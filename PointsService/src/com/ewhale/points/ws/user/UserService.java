package com.ewhale.points.ws.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.ewhale.points.common.annotations.VerifyToken;
import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ServiceException;
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.AgentFacade;
import com.ewhale.points.controller.facade.AgentRateFacade;
import com.ewhale.points.controller.facade.BranchFacade;
import com.ewhale.points.controller.facade.PointFacade;
import com.ewhale.points.controller.facade.PointsExchangeFacade;
import com.ewhale.points.controller.facade.ProductFacade;
import com.ewhale.points.controller.facade.ProfileFacade;
import com.ewhale.points.controller.facade.PromotionFacade;
import com.ewhale.points.controller.facade.PurchaseFacade;
import com.ewhale.points.ws.main.ServiceHeading;

@Path("/User")
@Produces(ServiceHeading.MEDIA_TYPE)
public class UserService implements ServiceHeading
{
	private UserServiceControler controler = new UserServiceControler();

	protected Logger LOG = Logger.getLogger(UserService.class);

	@EJB
	private AgentRateFacade agentRateFacade;

	@EJB
	private ProfileFacade profileFacade;

	@EJB
	private AgentFacade agentFacade;

	@EJB
	private BranchFacade branchFacade;

	@EJB
	private ProductFacade productFacade;

	@EJB
	private PromotionFacade promotionFacade;

	@EJB
	private PurchaseFacade purchaseFacade;

	@EJB
	private PointsExchangeFacade pointsExchangeFacade;

	@EJB
	private PointFacade pointFacade;

	@POST
	@Path("/register")
	public void register(Map<String, Object> registrationData)
	{
		try
		{
			registrationData.put(EntityConstants.Profile.roleId, EntityConstants.Role.Fixed.userRole.ID);
			controler.add(profileFacade, registrationData);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While Regstration", e.getStatusCode());
		}
		catch (ValidationException e)
		{
			throw new ServiceException(e.getMessage(), e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/addAgentRate")
	public void addAgentRate(Map<String, Object> rateData)
	{
		try
		{
			controler.add(agentRateFacade, rateData);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
		catch (ValidationException e)
		{
			throw new ServiceException(e.getMessage(), e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/updateUserProfile")
	public void updateUserProfile(Map<String, Object> userData)
	{
		try
		{
			userData.remove(EntityConstants.Branch.agentId);
			userData.remove(EntityConstants.Profile.roleId);
			controler.update(profileFacade, userData);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
		catch (ValidationException e)
		{
			throw new ServiceException(e.getMessage(), e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/agentsList")
	public List<Map<String, Object>> agentsList(Map<String, Object> data)
	{
		try
		{
			data.put(EntityConstants.Agent.statusId, EntityConstants.Status.Fixed.activeStatus.ID);
			//IMP_Ayman this should be removed if there is a timer that activate the agent or change it to pending
			long today=new Date().getTime();
			data.put(EntityConstants.Agent.contractEndDate_From_Search,today);
			data.put(EntityConstants.Agent.contractStartDate_To_Search,today);
			List<Map<String, Object>> agentsList = controler.getList(agentFacade, data);
			return agentsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/agentBranchesList")
	public List<Map<String, Object>> agentBranchesList(Map<String, Object> branchData)
	{
		try
		{
			List<Map<String, Object>> branchesList = controler.getList(branchFacade, branchData);
			return branchesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/agentProductsList")
	public List<Map<String, Object>> agentProductsList(Map<String, Object> productData)
	{
		try
		{
			productData.put(EntityConstants.Product.statusId, EntityConstants.Status.Fixed.activeStatus.ID);
			productData.put(EntityConstants.Product.validityDate_From_Search, new Date().getTime());
			List<Map<String, Object>> productsList = controler.getList(productFacade, productData);
			return productsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/agentPromotionsList")
	public List<Map<String, Object>> agentPromotionsList(Map<String, Object> promotionData)
	{
		try
		{
			promotionData.put(EntityConstants.Promotion.statusId, EntityConstants.Status.Fixed.activeStatus.ID);
			List<Map<String, Object>> promotionsList = controler.getList(promotionFacade, promotionData);
			return promotionsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/userPurchasesList")
	public List<Map<String, Object>> userPurchasesList(Map<String, Object> purchaseData)
	{
		try
		{
			List<Map<String, Object>> purchasesList = controler.getList(purchaseFacade, purchaseData);
			return purchasesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/userPointsExchangeList")
	public List<Map<String, Object>> userPointsExchangeList(Map<String, Object> pointsExchangeData)
	{
		try
		{
			List<Map<String, Object>> pointsExchangeList = controler.getList(pointsExchangeFacade, pointsExchangeData);
			return pointsExchangeList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/userGainedPointsList/{profileId}")
	public List<Map<String, Object>> userGainedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			List<Map<String, Object>> pointsList = controler.userGainedPointsList(pointFacade, id, pointsData);
			return pointsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/userReleasedPointsList/{profileId}")
	public List<Map<String, Object>> userReleasedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			List<Map<String, Object>> pointsList = controler.userReleasedPointsList(pointFacade, id, pointsData);
			return pointsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/userPointsList/{profileId}")
	public List<Map<String, Object>> userPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			pointsData.put(EntityConstants.Point.profileId, id);
			List<Map<String, Object>> pointsList = controler.getList(pointFacade, pointsData);
			return pointsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("userProfileDetails/{profileId}")
	public Map<String, Object> userProfileDetails(@PathParam("profileId") String profileId)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			Map<String, Object> profileData = controler.getDetails(profileFacade, id);
			return profileData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("agentDetails/{agentId}")
	public Map<String, Object> agentDetails(@PathParam("agentId") String agentId)
	{
		try
		{
			Long id = Long.valueOf(agentId);
			Map<String, Object> agentData = controler.getDetails(agentFacade, id);
			return agentData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("agentBranchDetails/{branchId}")
	public Map<String, Object> agentBranchDetails(@PathParam("branchId") String branchId)
	{
		try
		{
			Long id = Long.valueOf(branchId);
			Map<String, Object> branchData = controler.getDetails(branchFacade, id);
			return branchData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("agentProductDetails/{productId}")
	public Map<String, Object> agentProductDetails(@PathParam("productId") String productId)
	{
		try
		{
			Long id = Long.valueOf(productId);
			Map<String, Object> productData = controler.getDetails(productFacade, id);
			return productData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("agentPromotionDetails/{promotionId}")
	public Map<String, Object> agentPromotionDetails(@PathParam("promotionId") String promotionId)
	{
		try
		{
			Long id = Long.valueOf(promotionId);
			Map<String, Object> promotionData = controler.getDetails(promotionFacade, id);
			return promotionData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("/userAgentRatesList")
	public List<Map<String, Object>> userAgentRatesList(Map<String, Object> agentRateData)
	{
		try
		{
			List<Map<String, Object>> agentRatesList = controler.getList(agentRateFacade, agentRateData);
			return agentRatesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("userPurchaseDetails/{purchaseId}")
	public Map<String, Object> userPurchaseDetails(@PathParam("purchaseId") String purchaseId)
	{
		try
		{
			Long id = Long.valueOf(purchaseId);
			Map<String, Object> purchaseData = controler.getDetails(purchaseFacade, id);
			return purchaseData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("userPointsExchangeDetails/{pointsExchangeId}")
	public Map<String, Object> viewPointsExchangeDetails(@PathParam("pointsExchangeId") String pointsExchangeId)
	{
		try
		{
			Long id = Long.valueOf(pointsExchangeId);
			Map<String, Object> pointsExchangeData = controler.getDetails(pointsExchangeFacade, id);
			return pointsExchangeData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("userPointDetails/{pointId}")
	public Map<String, Object> userPointDetails(@PathParam("pointId") String pointId)
	{
		try
		{
			Long id = Long.valueOf(pointId);
			Map<String, Object> pointData = controler.getDetails(pointFacade, id);
			return pointData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("getSumProfilePoints/{profileId}")
	public int getSumProfilePoints(@PathParam("profileId") String profileId)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			int sumProfilePoints = controler.getSumProfilePoints(pointFacade, id);
			return sumProfilePoints;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("rejectPoints/{profileId}/{pointsId}")
	public void rejectPoints(@PathParam("pointsId") String pointsIdStr, @PathParam("profileId") String profileIdStr)
	{
		try
		{
			Long pointsId = Long.valueOf(pointsIdStr);
			Long profileId = Long.valueOf(profileIdStr);
			controler.rejectPoints(pointFacade, pointsId, profileId);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("confirmPointsId/{profileId}/{pointsId}")
	public int confirmPointsId(@PathParam("pointsId") String pointsIdStr, @PathParam("profileId") String profileIdStr)
	{
		try
		{
			Long pointsId = Long.valueOf(pointsIdStr);
			Long profileId = Long.valueOf(profileIdStr);
			return controler.confirmPoints(pointFacade, pointsId, profileId);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
		catch (ValidationException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e.getStatusCode());
		}
	}
	
	@POST
	@VerifyToken
	@Path("confirmPoints/{mobile}/{qrCode}")
	public Map<String, Object> confirmPoints(@PathParam("mobile") String mobile, @PathParam("qrCode") String qrCode)
	{
		try
		{
			return controler.confirmPoints(pointFacade, mobile, qrCode);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
		catch (ValidationException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e.getStatusCode());
		}
	}
}
