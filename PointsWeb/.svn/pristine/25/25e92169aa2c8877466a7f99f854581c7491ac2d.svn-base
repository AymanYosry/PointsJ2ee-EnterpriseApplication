package com.ewhale.points.web.managedbean.systemadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.BranchBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.system.proxy.SystemAdminServiceClient;

@SessionScoped
@ManagedBean
public class SysAdminAgentBranchBean extends BranchBean
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void getAgentBranches()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Branch.agentId, getAgentId());
		SystemAdminServiceClient systemAdminServiceClient = ServiceClientUtil.getSystemAdminServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> agentBranchesList = systemAdminServiceClient.getAgentBranchesList(data);
		populateTable(agentBranchesList, EntityConstants.Branch.branchName, EntityConstants.Branch.address);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		SystemAdminServiceClient systemAdminServiceClient = ServiceClientUtil.getSystemAdminServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> branchData = systemAdminServiceClient.branchDetails(data.get(EntityConstants.Branch.branchId) + "");
		fillDetailsData(branchData);
	}

	@Override
	protected String getDetailsPageName()
	{
		return "branch_details";
	}

	@Override
	protected void handlePostConstruct()
	{
		setCanAdd(false);
		setCanUpdate(false);
		setDialogeHeight(400);
		setDialogeWidth(750);
	}
}
