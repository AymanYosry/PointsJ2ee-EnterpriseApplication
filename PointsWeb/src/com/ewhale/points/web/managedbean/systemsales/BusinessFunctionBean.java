package com.ewhale.points.web.managedbean.systemsales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.AbsoluteBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.system.proxy.SystemSalesServiceClient;

@SessionScoped
@ManagedBean
public class BusinessFunctionBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short businessFunctionId;

	private String businessFunctionName;

	public BusinessFunctionBean()
	{
		setHasMoreDetails(false);
	}

	public Short getBusinessFunctionId()
	{
		return businessFunctionId;
	}

	public void setBusinessFunctionId(Short businessFunctionId)
	{
		this.businessFunctionId = businessFunctionId;
	}

	public String getBusinessFunctionName()
	{
		return businessFunctionName;
	}

	public void setBusinessFunctionName(String businessFunctionName)
	{
		this.businessFunctionName = businessFunctionName;
	}

	@Override
	protected void handlePostConstruct()
	{
		viewBusinessFunctionsList();
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		businessFunctionId = ((Number) data.get(EntityConstants.FunctionType.functionTypeId)).shortValue();
		businessFunctionName = (String) data.get(EntityConstants.FunctionType.functionTypeName);
	}

	@Override
	protected String getUpdatePageName()
	{
		return "function_details.xhtml";
	}

	@Override
	protected String getDetailsPageName()
	{
		return null;// has no more details return"function_add.xhtml";
	}

	public void viewBusinessFunctionsList()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> allBusinessFunctionsCollection = systemSalesServiceClient.getAllBusinessFunctions();
		populateTable(allBusinessFunctionsCollection, EntityConstants.FunctionType.functionTypeId, EntityConstants.FunctionType.functionTypeName);
	}

	public void addBusinessFunction()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.FunctionType.functionTypeName, businessFunctionName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.addBusinessFunction(data);
		viewBusinessFunctionsList();
		//IMP_Ahmed get Strings from aplication.properties file
		FacesUtil.growlInfoMessage("Success", "New record has been added successfully");

	}

	public void updateBusinessFunction()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.FunctionType.functionTypeId, businessFunctionId);
		data.put(EntityConstants.FunctionType.functionTypeName, businessFunctionName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.updateBusinessFunction(data);
		// IMP_Ahmed close update dialog and reload table data after saving.

	}

	public void deleteBusinessFunction()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.deleteBusinessFunction(businessFunctionId + "");

	}

	@Override
	protected void resetToAdd()
	{
		setBusinessFunctionId(null);
		setBusinessFunctionName(null);
	}
}
