package com.ewhale.points.ws.agent.proxy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/AgentAdmin")
@Consumes(MediaType.APPLICATION_JSON)
public interface AgentAdminServiceClient
{
	@POST
	@Path("/registerAgent")
	public void registerAgent(Map<String, Object> agentData);

	@POST
	@Path("/updateAgent")
	public void updateAgent(Map<String, Object> agentData);

	@POST
	@Path("/addAgentBranch")
	public void addAgentBranch(Map<String, Object> branchData);

	@POST
	@Path("/updateAgentBranch")
	public void updateAgentBranch(Map<String, Object> branchData);

	@POST
	@Path("/addAgentEmployee")
	public void addAgentEmployee(Map<String, Object> employeeData);

	@POST
	@Path("/updatedAgentEmployee")
	public void updateAgentEmployee(Map<String, Object> employeeData);

	@POST
	@Path("/addAgentProduct")
	public void addAgentProduct(Map<String, Object> productData);

	@POST
	@Path("/updateAgentProduct")
	public void updateAgentProduct(Map<String, Object> productData);

	@POST
	@Path("/addAgentPromotion")
	public void addAgentPromotion(Map<String, Object> promotionData);

	@POST
	@Path("/updateAgentPromotion")
	public void updateAgentPromotion(Map<String, Object> promotionData);

	@POST
	@Path("/updateAgentContract")
	public void updateAgentContract(Map<String, Object> contractData);

	@POST
	@Path("/agentContracts")
	public List<Map<String, Object>> getAgentContractsList(Map<String, Object> contractData);

	@POST
	@Path("/agentMessages")
	public List<Map<String, Object>> getAgentMessagesList(Map<String, Object> messageData);

	@POST
	@Path("/agentPromotions")
	public List<Map<String, Object>> getAgentPromotionsList(Map<String, Object> promotionData);

	@POST
	@Path("/agentEmployees")
	public List<Map<String, Object>> getAgentEmploeesList(Map<String, Object> employeeData);

	@POST
	@Path("/agentBranches")
	public List<Map<String, Object>> getAgentBranchesList(Map<String, Object> branchData);

	@POST
	@Path("/agentProducts")
	public List<Map<String, Object>> getAgentProductsList(Map<String, Object> productData);

	@POST
	@Path("/agentInvoicesList")
	public List<Map<String, Object>> agentInvoicesList(Map<String, Object> invoiceData);

	@POST
	@Path("/agentPaymentsList")
	public List<Map<String, Object>> agentPaymentsList(Map<String, Object> paymentData);

	@POST
	@Path("/agentPurchasesList")
	public List<Map<String, Object>> agentPurchasesList(Map<String, Object> purchasesData);

	@POST
	@Path("/agentPointsExchangeList")
	public List<Map<String, Object>> agentPointsExchangeList(Map<String, Object> pointsExchangeData);

	@POST
	@Path("/agentDetails/{agentId}")
	public Map<String, Object> agentDetails(@PathParam("agentId") String agentId);

	@POST
	@Path("messageDetails/{messageId}")
	public Map<String, Object> messageDetails(@PathParam("messageId") String messageId);

	@POST
	@Path("employeeDetails/{employeeId}")
	public Map<String, Object> employeeDetails(@PathParam("employeeId") String employeeId);

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
	@Path("viewInvoiceDetails/{invoiceId}")
	public Map<String, Object> viewInvoiceDetails(@PathParam("invoiceId") String invoiceId);

	@POST
	@Path("viewPaymentDetails/{paymentId}")
	public Map<String, Object> viewPaymentDetails(@PathParam("paymentId") String paymentId);

	@POST
	@Path("/viewBranchDetails/{branchId}")
	public Map<String, Object> viewBranchDetails(@PathParam("branchId") String branchId);

	@POST
	@Path("/deleteAgentProduct/{productId}")
	public void deleteAgentProduct(@PathParam("productId") String productId);

	@POST
	@Path("/deleteAgentPromotion/{promotionId}")
	public void deleteAgentPromotion(@PathParam("promotionId") String promotionId);

	@POST
	@Path("/deleteAgentBranch/{branchId}")
	public void deleteAgentBranch(@PathParam("branchId") String branchId);

	@POST
	@Path("/deleteAgentEmployee/{profileId}")
	public void deleteAgentEmployee(@PathParam("profileId") String profileId);

	@POST
	//IMP_Ahmed there is problem in below line
	//@VerifyToken
	@Path("agentPurchaseDetails/{purchaseId}")
	public Map<String, Object> agentPurchaseDetails(@PathParam("purchaseId") String purchaseId);

	@POST
	//@VerifyToken
	@Path("agentPointsExchangeDetails/{pointsExchangeId}")
	public Map<String, Object> viewAgentPointsExchangeDetails(@PathParam("pointsExchangeId") String pointsExchangeId);

	@POST
	//@VerifyToken
	@Path("agentBalance/{agentId}")
	public float viewAgentBalance(@PathParam("agentId") String agentId);

	@POST
	//@VerifyToken
	@Path("agentRate/{agentId}")
	public float viewAgentRate(@PathParam("agentId") String agentId);


	@POST
	//@VerifyToken
	@Path("updateAgentEmployeeRole/{profileId}/{roleId}")
	public void updateAgentEmployeeRole(@PathParam("profileId")String profileId, @PathParam("roleId")String roleId);
}