package com.ewhale.points.controller.facade;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.common.security.SecurityBuilder;
import com.ewhale.points.common.security.SecurityFactory;
import com.ewhale.points.common.stores.AGPasswordStore;
import com.ewhale.points.common.stores.TokentStore;
import com.ewhale.points.common.token.TokenGenerator;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.common.util.ExceptionConstants;
import com.ewhale.points.model.entity.Status;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * @author Ayman Yosry
 *         Session Bean implementation class LoginFacadeBean
 */
@Stateless
@Local(AuthenticationFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AuthenticationFacadeBean extends AbsoluteFacadeBean implements AuthenticationFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// protected org.jboss.logging.Logger LOG = Logger.getLogger(AuthenticationFacadeBean.class);

	@EJB
	private ItemStatusFacade itemStatusFacade;

	@EJB
	private ProfileFacade profileFacade;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.LOGIN) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	/**
	 * @param loginData
	 * @return
	 * @throws FacadeException
	 * @throws ValidationException
	 */
	@Override
	public Map<String, Object> login(Map<String, Object> loginData) throws FacadeException, ValidationException
	{
		Map<String, Object> userData = null;
		userData = validateUser(loginData);

		String token = null;
		if (userData != null)
		{
			// IMP_Ayman is there any relation between the pending user and reset password? if yes
			// what should we do if a pending user tried to login (when the agent admin add an employee , the employee will login while he is pending)
			short statusId = ((Number) userData.get(EntityConstants.Login.statusId)).shortValue();
			if (statusId == EntityConstants.Status.Fixed.pendingStatus.ID)
				throw new ValidationException(ExceptionConstants.UNAUTHENTCATED_USER_CREDENTIONALS_EX_MSG);
			if (statusId == EntityConstants.Status.Fixed.blockedStatus.ID)
				throw new ValidationException(ExceptionConstants.BLOCKED_USER_CREDENTIONALS_EX_MSG);
			try
			{
				token = genrateToken(userData);
			}
			catch (AuthenticationSecurityException e)
			{
				throw new FacadeException(e.getClass().getName() + "==> Facade Exception --> INVALID TOKEN ........ ", e);
			}
		}
		else
		{
			throw new ValidationException(ExceptionConstants.INVALID_USER_CREDENTIONALS_EX_MSG);
		}

		Map<String, Object> userProfile = getUserProfile(userData);
		userProfile.put(EntityConstants.Login.token, token);
		storeToken(userProfile);

		return userProfile;
	}

	/**
	 * @param data
	 * @throws EntityException
	 * @throws ValidationException 
	 */
	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException, ValidationException
	{
		try
		{
			changePassword(data);
		}
		catch (FacadeException e)
		{
			throw new EntityException(e);
		}
	}

	/**
	 * @param data
	 * @param idMap
	 * @throws ValidationException 
	 * @throws EntityException
	 */
	private void changePassword(Map<String, Object> data) throws FacadeException, ValidationException
	{
		Map<String, Object> userData = null;
		userData = validateUser(data);

		if (userData != null)
		{
			Object loginId = userData.get(EntityConstants.Login.loginId);
			Object mobileObj = userData.get(EntityConstants.Login.mobile);
			Object passwordObj = data.get(EntityConstants.Login.newPassword);
			short statusId = ((Number) userData.get(EntityConstants.Login.statusId)).shortValue();

			try
			{
				String password = (String) passwordObj;
				password = SecurityFactory.getSecurityBuilder(SecurityFactory.CMS_SECURITY_BUILDER).decrypt(password);

				data.put(EntityConstants.Login.loginId, loginId);
				data.put(EntityConstants.Login.password, password);
				data.remove(EntityConstants.Login.newPassword);

				if (statusId != EntityConstants.Status.Fixed.activeStatus.ID)
				{
					modifyUserStatus(loginId, EntityConstants.Status.Fixed.activeStatus.ID, false);
					releaseAGPassword((String) mobileObj);
				}
			}
			catch (AuthenticationSecurityException e)
			{
				// IMP_Ayman should we throw any validation exception here?
				throw new FacadeException(e);
			}
		}
		else
		{
			// IMP_Ayman if no user data what should we do here ?
		}
	}

	/**
	 * @param mobile
	 * @return
	 * @throws FacadeException
	 */
	@Override
	public void resetPassword(String mobile) throws FacadeException
	{
		try
		{
			String newPassword = generateRandomePassword();
			String hashedPassword = SecurityBuilder.passwordHashing(newPassword);
			storeAGPassword(mobile, hashedPassword);

			String smsMessage = FacadeBeanUtils.prepareSMS(newPassword);
			FacadeBeanUtils.sendSMS(mobile, smsMessage);
		}
		catch (Exception e)
		{
			throw new FacadeException(e);
		}
	}

	/**
	 * @param loginData
	 * @return
	 * @throws FacadeException
	 * @throws ValidationException 
	 */
	@Override
	public Map<String, Object> validateUser(Map<String, Object> loginData) throws FacadeException, ValidationException
	{
		Map<String, Object> userLoginData = null;
		String storedPassword = null;

		String mobile = (String) loginData.get(EntityConstants.Login.mobile);
		Object passwordObj = loginData.get(EntityConstants.Login.password);
		Object requestCodeObj = loginData.get(EntityConstants.Login.requestCode);
		// LOG.debug("password facade 203 :" + passwordObj);
		String password = (passwordObj != null) ? (String) passwordObj : (requestCodeObj != null) ? (String) requestCodeObj : null;
		userLoginData = getDBCredentials(mobile);

		if (userLoginData != null && password != null)
		{
			storedPassword = (String) userLoginData.get(EntityConstants.Login.password);
			storedPassword = storedPassword.trim();
			boolean isPasswordValid = validatePassword(mobile, password, storedPassword);
			if (isPasswordValid)
			{
				if (requestCodeObj != null && passwordObj == null)
				{
					Object loginId = userLoginData.get(EntityConstants.Login.loginId);
					modifyUserStatus(loginId, EntityConstants.Status.Fixed.pendingStatus.ID, true);
				}
			}
			else
			{
				userLoginData = null;
			}
		}

		return userLoginData;
	}
	
	/**
	 * 
	 * @param mobile
	 * @throws FacadeException
	 */
	@Override
	public void logout(String token) throws FacadeException
	{
		releaseToken(token);
	}

	/**
	 * @param mobile
	 * @param password
	 * @param storedPassword
	 * @return
	 * @throws FacadeException
	 */
	private boolean validatePassword(String mobile, String password, String storedPassword) throws FacadeException
	{
		boolean matched = false;
		String decryptedPassword = null;
		String storedAGPassword = null;
		try
		{
			matched = password.equals(storedPassword);
			if (!matched)
			{
				// LOG.debug("password facade 245 :" + password);
				decryptedPassword = SecurityFactory.getSecurityBuilder(SecurityFactory.CMS_SECURITY_BUILDER).decrypt(password);
				storedAGPassword = getAGPassword(mobile);
				matched = (storedAGPassword != null) ? decryptedPassword.equals(storedAGPassword) : false;
				if (!matched)
					matched = decryptedPassword.equals(storedPassword);
			}
		}
		catch (AuthenticationSecurityException e)
		{
			throw new FacadeException(e);
		}
		return matched;
	}

	/**
	 * @param mobile
	 * @return
	 */
	private Map<String, Object> getDBCredentials(String mobile) throws FacadeException
	{
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(EntityConstants.Login.mobile, mobile);

		List<Map<String, Object>> list = viewList(criteria);
		Map<String, Object> userLoginData = (list != null && list.size() > 0) ? list.get(0) : null;
		// dbLoginData.forEach((key, val) -> LOG.debug(" login -------------> " + key + " = " + val));
		return userLoginData;
	}

	/**
	 * @param userData
	 * @return
	 * @throws FacadeException
	 */
	private Map<String, Object> getUserProfile(Map<String, Object> userData) throws FacadeException
	{
		long profileId = ((Number) userData.get(EntityConstants.Login.loginId)).longValue();
		Map<String, Object> userProfile = profileFacade.viewDetails(profileId);

		return userProfile;
	}

	/**
	 * @param loginId
	 * @param statusId
	 * @throws FacadeException
	 * @throws ValidationException 
	 */
	@SuppressWarnings("unchecked")
	private void modifyUserStatus(Object itemId, short statusId, boolean newTransaction) throws FacadeException, ValidationException
	{
		Map<String, Object> data = new HashMap<>();
		if (newTransaction)
		{
			data.put(EntityConstants.Login.itemId, itemId);
			data.put(EntityConstants.Login.statusId, statusId);
			itemStatusFacade.update(data);
		}
		else
		{
			Status status = new Status();
			status.setStatusId(statusId);
			// Convert status id to status entity to be set in Item_Status Criteria
			data.put(EntityConstants.Login.status, status);

			Map<String, Object> criteria = new HashMap<>();
			// Set item id which is login id
			criteria.put(EntityConstants.Login.itemId, itemId);

			try
			{
				em.updateRecord(EntityClassEnum.ITEMSTATUS.entityClass, data, criteria);
			}
			catch (EntityException e)
			{
				throw new FacadeException(e);
			}
		}
	}

	/**
	 * @param loginData
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String genrateToken(Map<String, Object> credentialData) throws AuthenticationSecurityException
	{
		TokenGenerator token = new TokenGenerator();

		List tokenCredList = (List) credentialData.values().stream().collect(Collectors.toList());
		String hashedToken = token.generate(tokenCredList);

		return hashedToken;
	}

	/**
	 * @param result
	 */
	private void storeToken(Map<String, Object> result)
	{
		String token = (String) result.get(EntityConstants.Login.token);
		String mobile = (String) result.get(EntityConstants.Login.mobile);
		int roleId = ((Number) result.get(EntityConstants.Login.roleId)).intValue();

		Calendar currentDate = Calendar.getInstance();
		String[] data =
			{ mobile, roleId + "", currentDate.getTimeInMillis() + "" };

		TokentStore.TokenMap.put(token, data);
		// LOG.debug("*****" + token + "*****");
	}
	
	private void releaseToken(String token)
	{
		TokentStore.TokenMap.remove(token);
	}

	private String generateRandomePassword() throws AuthenticationSecurityException
	{
		String password = SecurityBuilder.getRandomPassword();
		return password;
	}

	private void storeAGPassword(String mobile, String newPassword)
	{
		AGPasswordStore.AGPasswordMap.put(mobile, newPassword);
	}

	private void releaseAGPassword(String mobile)
	{
		AGPasswordStore.AGPasswordMap.remove(mobile);
	}

	private String getAGPassword(String mobile)
	{
		String storedAGPassword = AGPasswordStore.AGPasswordMap.get(mobile);
		return storedAGPassword;
	}
}
