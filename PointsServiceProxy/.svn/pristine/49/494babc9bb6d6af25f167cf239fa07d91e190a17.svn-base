package com.ewhale.points.ws.main.proxy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/LookUp")
@Consumes(MediaType.APPLICATION_JSON)
public interface LookUpServiceClient
{
	@POST
	@Path("/statuses")
	public List<Map<String, Object>> getAllStatuses();

	@POST
	@Path("/businessFunctions")
	public List<Map<String, Object>> getAllBusinessFunctions();

	@POST
	@Path("/categories")
	public List<Map<String, Object>> getAllCategories();

	@POST
	@Path("/currencies")
	public List<Map<String, Object>> getAllCurrencies();

	@POST
	@Path("/paymentMethods")
	public List<Map<String, Object>> getAllPaymentMethods();

	@POST
	@Path("/roles")
	public List<Map<String, Object>> getAllRoles();

	@POST
	@Path("/districts")
	public List<Map<String, Object>> getAllDistricts(Map<String, Object> districtData);

	@POST
	@Path("/states")
	public List<Map<String, Object>> getAllStates(Map<String, Object> stateData);

	@POST
	@Path("/countries")
	public List<Map<String, Object>> getAllCountries();
}
