package com.ewhale.points.web.managedbean.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.BranchBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.LookUpServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

@SessionScoped
@ManagedBean
public class UserAgentBranchBean extends BranchBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Map<String, Object>> statesList;

	private List<Map<String, Object>> allAgentsList;

	public UserAgentBranchBean()
	{

	}

	public List<Map<String, Object>> getStatesList()
	{
		return statesList;
	}

	public void setStatesList(List<Map<String, Object>> statesList)
	{
		this.statesList = statesList;
	}

	public List<Map<String, Object>> getAgentsList()
	{
		return allAgentsList;
	}

	@Override
	protected void handlePostConstruct()
	{
		setCanAdd(false);
		setCanUpdate(false);
		loadStatesList();
		allAgentsList = UserBeanUtils.getAllAgentsList();
		setDialogeHeight(400);
		setDialogeWidth(750);
	}

	public void getAgentBranchesList()
	{
		Long agentId = getAgentId();
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Branch.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
		data.put(EntityConstants.Branch.agentId, agentId);
		data.put(EntityConstants.Branch.stateId, getStateId());
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> branchesList = userServiceClient.agentBranchesList(data);

		String[][] columnKeys = null;
		if (agentId == null)
			columnKeys = new String[][]
				{
						{ EntityConstants.Branch.agent, EntityConstants.Agent.tradeMark },
						{ EntityConstants.Branch.branchName, null },
						{ EntityConstants.Branch.tel, null },
						{ EntityConstants.Branch.state, EntityConstants.State.stateName },
						{ EntityConstants.Branch.address, null } };
		else
			columnKeys = new String[][]
				{
						{ EntityConstants.Branch.branchName, null },
						{ EntityConstants.Branch.tel, null },
						{ EntityConstants.Branch.state, EntityConstants.State.stateName },
						{ EntityConstants.Branch.address, null } };
		populateTable(branchesList, columnKeys);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> branchDetails = userServiceClient.agentBranchDetails(data.get(EntityConstants.Branch.branchId) + "");
		fillDetailsData(branchDetails);
	}

	@Override
	protected String getDetailsPageName()
	{
		return "branch_details";
	}

	public void loadStatesList()
	{
		LookUpServiceClient lookUpServiceClient = ServiceClientUtil.getLookUpServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.State.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
		statesList = lookUpServiceClient.getAllStates(data);
	}

	@Override
	public void resetParentPage()
	{
		super.resetParentPage();
		setAgentId(null);
	}

}
