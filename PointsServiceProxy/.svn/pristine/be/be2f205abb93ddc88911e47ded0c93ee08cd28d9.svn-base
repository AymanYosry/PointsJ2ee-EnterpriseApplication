package com.ewhale.points.ws.system.proxy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/SystemSales")
@Consumes(MediaType.APPLICATION_JSON)
public interface SystemSalesServiceClient
{
	@POST
	@Path("/addCountry")
	public void addCountry(Map<String, Object> countryData);

	@POST
	@Path("/updateCountry")
	public void updateCountry(Map<String, Object> countryData);
	

	@POST
	@Path("/deleteCountry/{countryId}")
	public void deleteCountry(@PathParam("countryId") String countryId);

	@POST
	@Path("/countries")
	public List<Map<String, Object>> getAllCountries();

	@POST
	@Path("/addState")
	public void addState(Map<String, Object> countryData);

	@POST
	@Path("/updateState")
	public void updateState(Map<String, Object> countryData);

	@POST
	@Path("/deleteState/{stateId}")
	public void deleteState(@PathParam("stateId") String stateId);
	
	@POST
	@Path("/stateDetails/{stateId}")
	public Map<String, Object> stateDetails(@PathParam("stateId") String stateId);

	@POST
	@Path("/states")
	public List<Map<String, Object>> getStatesList(Map<String, Object> stateData);

	@POST
	@Path("/addDistrict")
	public void addDistrict(Map<String, Object> districtData);

	@POST
	@Path("/updateDistrict")
	public void updateDistrict(Map<String, Object> districtData);

	@POST
	@Path("/deleteDistrict/{districtId}")
	public void deleteDistrict(@PathParam("districtId") String districtId);
	
	@POST
	@Path("/districtDetails/{districtId}")
	public Map<String, Object> districtDetails(@PathParam("districtId") String districtId);

	@POST
	@Path("/districts")
	public List<Map<String, Object>> getDistrictsList(Map<String, Object> districtData);

	@POST
	@Path("/addRole")
	public void addRole(Map<String, Object> roleData);

	@POST
	@Path("/updateRole")
	public void updateRole(Map<String, Object> roleData);

	@POST
	@Path("/deleteRole/{roleId}")
	public void deleteRole(@PathParam("roleId") String roleId);

	@POST
	@Path("/roles")
	public List<Map<String, Object>> getAllRoles();

	@POST
	@Path("/addPaymentMethod")
	public void addPaymentMethod(Map<String, Object> paymentMethodData);

	@POST
	@Path("/updatePaymentMethod")
	public void updatePaymentMethod(Map<String, Object> paymentMethodData);

	@POST
	@Path("/deletePaymentMethod/{paymentMethodId}")
	public void deletePaymentMethod(@PathParam("paymentMethodId") String paymentMethodId);
	
	@POST
	@Path("/paymentMethodDetails/{methodId}")
	public Map<String, Object> paymentMethodDetails(@PathParam("methodId") String methodId);

	@POST
	@Path("/paymentMethods")
	public List<Map<String, Object>> getAllPaymentMethods();

	@POST
	@Path("/addCurrency")
	public void addCurrency(Map<String, Object> currencyData);

	@POST
	@Path("/updateCurrency")
	public void updateCurrency(Map<String, Object> currencyData);

	@POST
	@Path("/deleteCurrency/{currencyId}")
	public void deleteCurrency(@PathParam("currencyId") String currencyId);

	@POST
	@Path("/currencies")
	public List<Map<String, Object>> getAllCurrencies();

	@POST
	@Path("/currencyDetails/{currencyId}")
	public Map<String, Object> currencyDetails(@PathParam("currencyId") String currencyId);

	@POST
	@Path("/addCategory")
	public void addCategory(Map<String, Object> categoryData);

	@POST
	@Path("/updateCategory")
	public void updateCategory(Map<String, Object> categoryData);

	@POST
	@Path("/deleteCategory/{categoryId}")
	public void deleteCategory(@PathParam("categoryId") String categoryId);

	@POST
	@Path("/categories")
	public List<Map<String, Object>> getAllCategories();

	@POST
	@Path("/addBusinessFunction")
	public void addBusinessFunction(Map<String, Object> businessFunctionData);

	@POST
	@Path("/updateBusinessFunction")
	public void updateBusinessFunction(Map<String, Object> businessFunctionData);

	@POST
	@Path("/deleteBusinessFunction/{businessFunctionId}")
	public void deleteBusinessFunction(@PathParam("businessFunctionId") String businessFunctionId);
	

	@POST
	@Path("/businessFunctions")
	public List<Map<String, Object>> getAllBusinessFunctions();

	@POST
	@Path("/addStatus")
	public void addStatus(Map<String, Object> statusData);

	@POST
	@Path("/updateStatus")
	public void updateStatus(Map<String, Object> statusData);

	@POST
	@Path("/deleteStatus/{statusId}")
	public void deleteStatus(@PathParam("statusId") String statusId);

	@POST
	@Path("/statuses")
	public List<Map<String, Object>> getAllStatuses();

	@POST
	@Path("/addSysEvent")
	public void addSysEvent(Map<String, Object> sysEventData);

	@POST
	@Path("/updateSysEvent")
	public void updateSysEvent(Map<String, Object> sysEventData);

	@POST
	@Path("/deleteSysEvent/{sysEventId}")
	public void deleteSysEvent(@PathParam("sysEventId") String sysEventId);

	@POST
	@Path("/viewEventsList")
	public List<Map<String, Object>> viewEventsList(Map<String, Object> sysEventData);

	@POST
	@Path("/eventDetails/{sysEventId}")
	public Map<String, Object> eventDetails(@PathParam("sysEventId") String sysEventId);
}