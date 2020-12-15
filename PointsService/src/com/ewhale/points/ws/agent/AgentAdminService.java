/**
 * 
 */
package com.ewhale.points.ws.agent;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.ewhale.points.controller.facade.ContractFacade;
import com.ewhale.points.controller.facade.MessageCenterFacade;
import com.ewhale.points.controller.facade.PointsExchangeFacade;
import com.ewhale.points.controller.facade.ProductFacade;
import com.ewhale.points.controller.facade.ProfileFacade;
import com.ewhale.points.controller.facade.PromotionFacade;
import com.ewhale.points.controller.facade.PurchaseFacade;
import com.ewhale.points.controller.facade.SysInvoiceFacade;
import com.ewhale.points.controller.facade.SysPaymentFacade;
import com.ewhale.points.ws.main.ServiceHeading;

/**
 * @author Ayman Yosry
 */

@Path("/AgentAdmin")
@VerifyToken
@Produces(ServiceHeading.MEDIA_TYPE)
public class AgentAdminService implements ServiceHeading
{
	AgentServiceControler controler = new AgentServiceControler();

	protected Logger LOG = Logger.getLogger(AgentAdminService.class);

	@EJB
	private AgentFacade agentFacade;

	@EJB
	private AgentRateFacade agentRateFacade;

	@EJB
	private BranchFacade branchFacade;

	@EJB
	private ProfileFacade profileFacade;

	@EJB
	private ProductFacade productFacade;

	@EJB
	private PromotionFacade promotionFacade;

	@EJB
	private ContractFacade contractFacade;

	@EJB
	private MessageCenterFacade messageCenterFacade;

	@EJB
	private SysInvoiceFacade sysInvoiceFacade;

	@EJB
	private SysPaymentFacade sysPaymentFacade;

	@EJB
	private PurchaseFacade purchaseFacade;

	@EJB
	private PointsExchangeFacade pointsExchangeFacade;

	@POST
	@Path("/registerAgent")
	public void registerAgent(Map<String, Object> agentData)
	{
		try
		{
			controler.addAgent(agentFacade, agentData);
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
	@Path("/updateAgent")
	public void updateAgent(Map<String, Object> agentData)
	{
		try
		{
			controler.update(agentFacade, agentData);
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
	@Path("/addAgentBranch")
	public void addAgentBranch(Map<String, Object> branchData)
	{
		try
		{
			controler.add(branchFacade, branchData);
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
	@Path("/updateAgentBranch")
	public void updateAgentBranch(Map<String, Object> branchData)
	{
		try
		{
			controler.update(branchFacade, branchData);
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
	@Path("/addAgentEmployee")
	public void addAgentEmployee(Map<String, Object> employeeData)
	{
		try
		{
			employeeData.put(EntityConstants.Profile.roleId, EntityConstants.Role.Fixed.agentSellerRole.ID);
			controler.add(profileFacade, employeeData);
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
	@Path("/updatedAgentEmployee")
	public void updateAgentEmployee(Map<String, Object> employeeData)
	{
		try
		{
			controler.update(profileFacade, employeeData);
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
	@Path("/addAgentProduct")
	public void addAgentProduct(Map<String, Object> productData)
	{
		try
		{
			controler.add(productFacade, productData);
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
	@Path("/updateAgentProduct")
	public void updateAgentProduct(Map<String, Object> productData)
	{
		try
		{
			controler.update(productFacade, productData);
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
	@Path("/addAgentPromotion")
	public void addAgentPromotion(Map<String, Object> promotionData)
	{
		try
		{
			controler.add(promotionFacade, promotionData);
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
	@Path("/updateAgentPromotion")
	public void updateAgentPromotion(Map<String, Object> promotionData)
	{
		try
		{
			controler.update(promotionFacade, promotionData);
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
	@Path("/updateAgentContract")
	public void updateAgentContract(Map<String, Object> contractData)
	{
		try
		{
			controler.update(contractFacade, contractData);
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
	@Path("/agentContracts")
	public List<Map<String, Object>> getAgentContractsList(Map<String, Object> contractData)
	{
		try
		{
			List<Map<String, Object>> contractsList = controler.getList(contractFacade, contractData);
			return contractsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentMessages")
	public List<Map<String, Object>> getAgentMessagesList(Map<String, Object> messageData)
	{
		try
		{
			List<Map<String, Object>> messageList = controler.getList(messageCenterFacade, messageData);
			return messageList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentPromotions")
	public List<Map<String, Object>> getAgentPromotionsList(Map<String, Object> promotionData)
	{
		try
		{
			List<Map<String, Object>> promotionList = controler.getList(promotionFacade, promotionData);
			return promotionList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentEmployees")
	public List<Map<String, Object>> getAgentEmploeesList(Map<String, Object> employeeData)
	{
		try
		{
			Number roleId = (Number) employeeData.get(EntityConstants.Profile.roleId);
			if (roleId == null)
			{
				List<Integer> roleIds = new ArrayList<>();
				roleIds.add(EntityConstants.Role.Fixed.agentAdminRole.ID);
				roleIds.add(EntityConstants.Role.Fixed.agentSellerRole.ID);
				employeeData.put(EntityConstants.Profile.roleIds, roleIds);
			}
			List<Map<String, Object>> employeesList = controler.getList(profileFacade, employeeData);
			return employeesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentBranches")
	public List<Map<String, Object>> getAgentBranchesList(Map<String, Object> branchData)
	{
		try
		{
			List<Map<String, Object>> branchList = controler.getList(branchFacade, branchData);
			return branchList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentProducts")
	public List<Map<String, Object>> getAgentProductsList(Map<String, Object> productData)
	{
		try
		{
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
	@Path("/agentInvoicesList")
	public List<Map<String, Object>> agentInvoicesList(Map<String, Object> invoiceData)
	{
		try
		{
			List<Map<String, Object>> invoicesList = controler.getList(sysInvoiceFacade, invoiceData);
			return invoicesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentPaymentsList")
	public List<Map<String, Object>> agentPaymentsList(Map<String, Object> paymentData)
	{
		try
		{
			List<Map<String, Object>> paymentsList = controler.getList(sysPaymentFacade, paymentData);
			return paymentsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentPurchasesList")
	public List<Map<String, Object>> agentPurchasesList(Map<String, Object> purchasesData)
	{
		try
		{
			List<Map<String, Object>> purchasesList = controler.getList(purchaseFacade, purchasesData);
			return purchasesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentPointsExchangeList")
	public List<Map<String, Object>> agentPointsExchangeList(Map<String, Object> pointsExchangeData)
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
	@Path("/agentDetails/{agentId}")
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
	@Path("messageDetails/{messageId}")
	public Map<String, Object> messageDetails(@PathParam("messageId") String messageId)
	{
		try
		{
			Long id = Long.valueOf(messageId);
			Map<String, Object> messageData = controler.getDetails(messageCenterFacade, id);
			return messageData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@Path("employeeDetails/{employeeId}")
	public Map<String, Object> employeeDetails(@PathParam("employeeId") String employeeId)
	{
		try
		{
			Long id = Long.valueOf(employeeId);
			Map<String, Object> employeeData = controler.getDetails(profileFacade, id);
			return employeeData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@Path("contractDetails/{contractId}")
	public Map<String, Object> contractDetails(@PathParam("contractId") String contractId)
	{
		try
		{
			Long id = Long.valueOf(contractId);
			Map<String, Object> contractData = controler.getDetails(contractFacade, id);
			return contractData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@Path("productDetails/{productId}")
	public Map<String, Object> productDetails(@PathParam("productId") String productId)
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
	@Path("promotionDetails/{promotionId}")
	public Map<String, Object> promotionDetails(@PathParam("promotionId") String promotionId)
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
	@Path("viewInvoiceDetails/{invoiceId}")
	public Map<String, Object> viewInvoiceDetails(@PathParam("invoiceId") String invoiceId)
	{
		try
		{
			Long id = Long.valueOf(invoiceId);
			Map<String, Object> invoiceData = controler.getDetails(sysInvoiceFacade, id);
			return invoiceData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@Path("viewPaymentDetails/{paymentId}")
	public Map<String, Object> viewPaymentDetails(@PathParam("paymentId") String paymentId)
	{
		try
		{
			Long id = Long.valueOf(paymentId);
			Map<String, Object> paymentData = controler.getDetails(sysPaymentFacade, id);
			return paymentData;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@Path("/viewBranchDetails/{branchId}")
	public Map<String, Object> viewBranchDetails(@PathParam("branchId") String branchId)
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
	@Path("/deleteAgentProduct/{productId}")
	public void deleteAgentProduct(@PathParam("productId") String productId)
	{
		try
		{
			Long id = Long.valueOf(productId);
			controler.delete(productFacade, id);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/deleteAgentPromotion/{promotionId}")
	public void deleteAgentPromotion(@PathParam("promotionId") String promotionId)
	{
		try
		{
			Long id = Long.valueOf(promotionId);
			controler.delete(promotionFacade, id);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/deleteAgentBranch/{branchId}")
	public void deleteAgentBranch(@PathParam("branchId") String branchId)
	{
		try
		{
			Long id = Long.valueOf(branchId);
			controler.delete(branchFacade, id);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/deleteAgentEmployee/{profileId}")
	public void deleteAgentEmployee(@PathParam("profileId") String profileId)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			controler.delete(profileFacade, id);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("agentPurchaseDetails/{purchaseId}")
	public Map<String, Object> agentPurchaseDetails(@PathParam("purchaseId") String purchaseId)
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
	@Path("agentPointsExchangeDetails/{pointsExchangeId}")
	public Map<String, Object> viewAgentPointsExchangeDetails(@PathParam("pointsExchangeId") String pointsExchangeId)
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
	@Path("agentBalance/{agentId}")
	public float viewAgentBalance(@PathParam("agentId") String agentId)
	{
		try
		{
			Long id = Long.valueOf(agentId);
			float agentBalance = controler.getAgentBalance(sysPaymentFacade, id);
			return agentBalance;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}

	}

	@POST
	@VerifyToken
	@Path("agentRate/{agentId}")
	public float viewAgentRate(@PathParam("agentId") String agentId)
	{
		try
		{
			Long id = Long.valueOf(agentId);
			float agentRate = controler.getAgentRate(agentRateFacade, id);
			return agentRate;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("updateAgentEmployeeRole/{profileId}/{roleId}")
	public void updateAgentEmployeeRole(@PathParam("profileId") String profileId, @PathParam("roleId") String roleId)
	{

		try
		{
			Map<String, Object> profileData = new HashMap<>();
			profileData.put(EntityConstants.Profile.profileId, Long.parseLong(profileId));
			profileData.put(EntityConstants.Profile.roleId, Integer.parseInt(roleId));
			controler.update(profileFacade, profileData);
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
}
