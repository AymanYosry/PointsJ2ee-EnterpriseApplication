package com.ewhale.points.ws.agent.proxy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/AgentSeller")
@Consumes(MediaType.APPLICATION_JSON)
public interface AgentSellerServiceClient
{
	@POST
	@Path("/fundUserPurchase")
	public Map<String, Object> fundUserPurchase(Map<String, Object> data);

	@POST
	@Path("/refundUserPurchase")
	public Map<String, Object> refundUserPurchase(Map<String, Object> data);

	@POST
	@Path("/exchangeUserPoints")
	public Map<String, Object> exchangeUserPoints(Map<String, Object> data);

	@POST
	@Path("/reExchangeUserPoints")
	public Map<String, Object> reExchangeUserPoints(Map<String, Object> data);

	@POST
	@Path("/userPurchasesList")
	public List<Map<String, Object>> userPurchasesList(Map<String, Object> purchaseData);

	@POST
	@Path("/userExchangesList")
	public List<Map<String, Object>> userExchangesList(Map<String, Object> exchangeData);

	@POST
	@Path("/agentProductsList")
	public List<Map<String, Object>> agentProductsList(Map<String, Object> productData);

	@POST
	@Path("/agentPromotionsList")
	public List<Map<String, Object>> agentPromotionsList(Map<String, Object> promotionData);

	@POST
	@Path("/agentBranchesList")
	public List<Map<String, Object>> agentBranchesList(Map<String, Object> branchesData);

	@POST
	@Path("viewPurchaseDetails/{purchaseId}")
	public Map<String, Object> viewPurchaseDetails(@PathParam("purchaseId") String purchaseId);

	@POST
	@Path("viewExchangeDetails/{exchangeId}")
	public Map<String, Object> viewExchangeDetails(@PathParam("exchangeId") String exchangeId);

	@POST
	@Path("/agentRateList/{exchangeId}")
	public List<Map<String, Object>> agentRateList(@PathParam("exchangeId") String agentId);

	@POST
	@Path("viewAgentDetails/{agentId}")
	public Map<String, Object> viewAgentDetails(@PathParam("agentId") String agentId);

	@POST
	@Path("viewAgentContractDetails/{contractId}")
	public Map<String, Object> viewAgentContractDetails(@PathParam("contractId") String contractId);

	@POST
	@Path("viewBranchDetails/{branchId}")
	public Map<String, Object> viewBranchDetails(@PathParam("branchId") String branchId);

	@POST
	@Path("viewProductDetails/{productId}")
	public Map<String, Object> viewProductDetails(@PathParam("productId") String productId);

	@POST
	@Path("viewPromotionDetails/{promotionId}")
	public Map<String, Object> viewPromotionDetails(@PathParam("promotionId") String promotionId);
}
