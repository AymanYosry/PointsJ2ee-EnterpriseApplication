package com.ewhale.points.ws.system.proxy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/SystemAdmin")
@Consumes(MediaType.APPLICATION_JSON)
public interface SystemAdminServiceClient
{
	@POST
	@Path("/addSysAdminEmplyee")
	public void addSysAdminEmplyee(Map<String, Object> profileData);

	@POST
	@Path("/addSysSalesEmplyee")
	public void addSysSalesEmplyee(Map<String, Object> profileData);

	@POST
	@Path("/addAgentAdminEmployee")
	public void addAgentAdminEmployee(Map<String, Object> profileData);

	@POST
	@Path("/addAgent")
	public void addAgent(Map<String, Object> agentData);

	@POST
	@Path("/updateAgent")
	public void updateAgent(Map<String, Object> agentData);

	@POST
	@Path("/addAgentContract")
	public void addAgentContract(Map<String, Object> agentContractData);

	@POST
	@Path("/updateAgentContract")
	public void updateAgentContract(Map<String, Object> agentContractData);

	@POST
	@Path("/addAgentPayment")
	public void addAgentPayment(Map<String, Object> agentPaymentData);

	@POST
	@Path("/updateItemStatus")
	public void updateItemStatus(Map<String, Object> itemStatusData);

	@POST
	@Path("/profiles")
	public List<Map<String, Object>> getProfilesList(Map<String, Object> profileData);
	@POST
	@Path("/employees")
	public List<Map<String, Object>> getEmployeeList(Map<String, Object> profileData);

	@POST
	@Path("/purchases")
	public List<Map<String, Object>> getPurchasesList(Map<String, Object> purchasesData);

	@POST
	@Path("/agents")
	public List<Map<String, Object>> getAgentsList(Map<String, Object> agentsData);

	@POST
	@Path("/agentBranches")
	public List<Map<String, Object>> getAgentBranchesList(Map<String, Object> brancheData);

	@POST
	@Path("/agentProducts")
	public List<Map<String, Object>> getAgentProductsList(Map<String, Object> productData);

	@POST
	@Path("/agentInvoices")
	public List<Map<String, Object>> getAgentInvoicesList(Map<String, Object> invoiceData);

	@POST
	@Path("/agentPayments")
	public List<Map<String, Object>> getAgentPaymentsList(Map<String, Object> paymentData);

	@POST
	@Path("/agentContracts")
	public List<Map<String, Object>> getAgentContractsList(Map<String, Object> contractData);

	@POST
	@Path("/agentEmployees")
	public List<Map<String, Object>> getAgentEmployeeList(Map<String, Object> profileData);
	
	@POST
	@Path("/messages")
	public List<Map<String, Object>> getMessagesList(Map<String, Object> messageData);

	@POST
	@Path("/promotions")
	public List<Map<String, Object>> getPromotionsList(Map<String, Object> promotionData);

	@POST
	@Path("/profileDetails/{profileId}")
	public Map<String, Object> profileDetails(@PathParam("profileId") String profileId);

	@POST
	@Path("/purchaseDetails/{purchaseId}")
	public Map<String, Object> purchaseDetails(@PathParam("purchaseId") String purchaseId);

	@POST
	@Path("/agentDetails/{agentId}")
	public Map<String, Object> agentDetails(@PathParam("agentId") String agentId);

	@POST
	@Path("/branchDetails/{branchId}")
	public Map<String, Object> branchDetails(@PathParam("branchId") String branchId);

	@POST
	@Path("contractDetails/{contractId}")
	public Map<String, Object> contractDetails(@PathParam("contractId") String contractId);

	@POST
	@Path("productDetails/{productId}")
	public Map<String, Object> productDetails(@PathParam("productId") String productId);

	@POST
	@Path("promotionDetails/{promotionId}")
	public Map<String, Object> promotionDetails(@PathParam("promotionId") String promotionId);

	@POST
	@Path("invoiceDetails/{invoiceId}")
	public Map<String, Object> invoiceDetails(@PathParam("invoiceId") String invoiceId);

	@POST
	@Path("paymentDetails/{paymentId}")
	public Map<String, Object> paymentDetails(@PathParam("paymentId") String paymentId);

	@POST
	@Path("messageDetails/{messageId}")
	public Map<String, Object> messageDetails(@PathParam("messageId") String messageId);

	@POST
	@Path("/deleteProfile/{profileId}")
	public void deleteProfile(@PathParam("profileId") String profileId);

	@POST
	@Path("/deleteAgent/{agentId}")
	public void deleteAgent(@PathParam("agentId") String agentId);

	@POST
	@Path("/deleteContract/{contractId}")
	public void deleteContract(@PathParam("contractId") String contractId);

	@POST
	@Path("updateSysEmployeeRole/{profileId}/{roleId}")
	public void updateSysEmployeeRole(@PathParam("profileId") String profileId, @PathParam("roleId") String roleId);

	@POST
	@Path("getSumProfilePoints/{profileId}")
	public int getSumProfilePoints(@PathParam("profileId") String profileId);
	
	@POST
	@Path("/userGainedPointsList/{profileId}")
	public List<Map<String, Object>> userGainedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);

	@POST
	@Path("/userReleasedPointsList/{profileId}")
	public List<Map<String, Object>> userReleasedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);
	
	@POST
	@Path("/userPointsList/{profileId}")
	public List<Map<String, Object>> userPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);
}
