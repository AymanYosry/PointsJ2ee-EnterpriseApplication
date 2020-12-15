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
public class StatusBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short statusId;

	private String statusName;

	public StatusBean()
	{
		setHasMoreDetails(false);
	}

	public Short getStatusId()
	{
		return statusId;
	}

	public void setStatusId(Short statusId)
	{
		this.statusId = statusId;
	}

	public String getStatusName()
	{
		return statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

	@Override
	protected String getDetailsPageName()
	{
		return null; // has no more details
	}
	
	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		statusId = ((Number)data.get(EntityConstants.Status.statusId)).shortValue();
		statusName = (String)data.get(EntityConstants.Status.statusName);
	}
	
	@Override
	protected void handlePostConstruct()
	{
		viewStatusesList();
	}
	
	@Override
	protected String getUpdatePageName()
	{
		return "status_details.xhtml";
	}

	public void viewStatusesList()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> allStatusesCollection = systemSalesServiceClient.getAllStatuses();
		populateTable(allStatusesCollection, EntityConstants.Status.statusId, EntityConstants.Status.statusName);
	}

	public void addStatus()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Status.statusName, statusName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.addStatus(data);

	}

	public void updateStatus()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Status.statusId, statusId);
		data.put(EntityConstants.Status.statusName, statusName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.updateStatus(data);

	}

	public void deleteStatus()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.deleteStatus(statusId + "");
	}
	
	@Override
	protected void resetToAdd()
	{
		setStatusId(null);
		setStatusName(null);
	}

}
