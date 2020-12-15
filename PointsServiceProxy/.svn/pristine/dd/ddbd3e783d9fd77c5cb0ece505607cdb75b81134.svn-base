package com.ewhale.points.ws.user.proxy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/User")
@Consumes(MediaType.APPLICATION_JSON)
public interface UserServiceClient
{
	@POST
	@Path("/register")
	public void register(Map<String, Object> registrationData);

	@POST
	@Path("/addAgentRate")
	public void addAgentRate(Map<String, Object> rateData);

	@POST
	@Path("/updateUserProfile")
	public void updateUserProfile(Map<String, Object> userData);

	@POST
	@Path("/agentsList")
	public List<Map<String, Object>> agentsList(Map<String, Object> data);

	@POST
	@Path("/agentBranchesList")
	public List<Map<String, Object>> agentBranchesList(Map<String, Object> branchData);

	@POST
	@Path("/agentProductsList")
	public List<Map<String, Object>> agentProductsList(Map<String, Object> productData);

	@POST
	@Path("/agentPromotionsList")
	public List<Map<String, Object>> agentPromotionsList(Map<String, Object> promotionData);

	@POST
	@Path("/userPurchasesList")
	public List<Map<String, Object>> userPurchasesList(Map<String, Object> purchaseData);

	@POST
	@Path("/userPointsExchangeList")
	public List<Map<String, Object>> userPointsExchangeList(Map<String, Object> pointsExchangeData);

	@POST
	@Path("/userGainedPointsList/{profileId}")
	public List<Map<String, Object>> userGainedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);

	@POST
	@Path("/userReleasedPointsList/{profileId}")
	public List<Map<String, Object>> userReleasedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);

	@POST
	@Path("/userPointsList/{profileId}")
	public List<Map<String, Object>> userPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);

	@POST
	@Path("agentDetails/{agentId}")
	public Map<String, Object> agentDetails(@PathParam("agentId") String agentId);

	@POST
	@Path("agentBranchDetails/{branchId}")
	public Map<String, Object> agentBranchDetails(@PathParam("branchId") String branchId);

	@POST
	@Path("agentProductDetails/{productId}")
	public Map<String, Object> agentProductDetails(@PathParam("productId") String productId);

	@POST
	@Path("agentPromotionDetails/{promotionId}")
	public Map<String, Object> agentPromotionDetails(@PathParam("promotionId") String promotionId);

	@POST
	@Path("/userAgentRatesList")
	public List<Map<String, Object>> userAgentRatesList(Map<String, Object> agentRateData);

	@POST
	@Path("userPurchaseDetails/{purchaseId}")
	public Map<String, Object> userPurchaseDetails(@PathParam("purchaseId") String purchaseId);

	@POST
	@Path("userPointsExchangeDetails/{pointsExchangeId}")
	public Map<String, Object> viewPointsExchangeDetails(@PathParam("pointsExchangeId") String pointsExchangeId);

	@POST
	@Path("userPointDetails/{pointId}")
	public Map<String, Object> userPointDetails(@PathParam("pointId") String pointId);

	@POST
	@Path("userProfileDetails/{profileId}")
	public Map<String, Object> userProfileDetails(@PathParam("profileId") String profileId);

	@POST
	@Path("getSumProfilePoints/{profileId}")
	public int getSumProfilePoints(@PathParam("profileId") String profileId);

	@POST
	@Path("rejectPoints/{profileId}/{pointsId}")
	public void rejectPoints(@PathParam("pointsId") String pointsIdStr, @PathParam("profileId") String profileIdStr);

	@POST
	@Path("confirmPointsId/{profileId}/{pointsId}")
	public int confirmPointsId(@PathParam("pointsId") String pointsIdStr, @PathParam("profileId") String profileIdStr);

	@POST
	@Path("confirmPoints/{mobile}/{qrCode}")
	public Map<String, Object> confirmPoints(@PathParam("mobile") String mobile, @PathParam("qrCode") String qrCode);
}
