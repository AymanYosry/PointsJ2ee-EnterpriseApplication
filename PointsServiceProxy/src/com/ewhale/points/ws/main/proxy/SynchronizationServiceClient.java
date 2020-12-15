package com.ewhale.points.ws.main.proxy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.ewhale.points.common.annotations.VerifyToken;

@Path("/Synchronization")
@Consumes(MediaType.APPLICATION_JSON)
public interface SynchronizationServiceClient
{

	@POST
	@Path("/districts")
	public List<Map<String, Object>> getAllDistricts(Map<String, Object> districtData);

	@POST
	@Path("/states")
	public List<Map<String, Object>> getAllStates(Map<String, Object> stateData);

	@POST
	@Path("/countries")
	public List<Map<String, Object>> getAllCountries();

	@POST
	@Path("/categories")
	public List<Map<String, Object>> getAllCategories();

	@POST
	@Path("/agentsList")
	public List<Map<String, Object>> getAgentsList(Map<String, Object> data);

	@POST
	@Path("/branchesList")
	public List<Map<String, Object>> getBranchesList(Map<String, Object> data);

	@POST
	@Path("/productsList")
	public List<Map<String, Object>> getProductsList(Map<String, Object> data);

	@POST
	@Path("/promotionsList")
	public List<Map<String, Object>> getPromotionsList(Map<String, Object> data);

	@POST
	@Path("/synchronizationNeeded/{date}/{profileId}")
	public Integer[] isSynchronizationNeeded(@PathParam("date") String dateInTimeMillisStr, @PathParam("profileId") String profileIdStr);

	@POST
	@VerifyToken
	@Path("/userPurchasesList/{lastSyncDate}/{profileId}/{lastPurchaseId}")
	public List<Map<String, Object>> userPurchasesList(@PathParam("lastSyncDate") String lastSyncDateInTimeMillisStr,
			@PathParam("profileId") String profileId, @PathParam("lastPurchaseId") String lastPurchaseId);

	@POST
	@VerifyToken
	@Path("/userPointsExchangeList/{lastSyncDate}/{profileId}/{lastPointsExchangeId}")
	public List<Map<String, Object>> userPointsExchangeList(@PathParam("lastSyncDate") String lastSyncDateInTimeMillisStr,
			@PathParam("profileId") String profileId, @PathParam("lastPointsExchangeId") String lastPointsExchangeId);

	@POST
	@VerifyToken
	@Path("/userGainedPointsList/{profileId}")
	public List<Map<String, Object>> userGainedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);

	@POST
	@VerifyToken
	@Path("/userReleasedPointsList/{profileId}")
	public List<Map<String, Object>> userReleasedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData);

}
