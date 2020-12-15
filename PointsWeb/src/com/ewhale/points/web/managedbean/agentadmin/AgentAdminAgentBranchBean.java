package com.ewhale.points.web.managedbean.agentadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.BranchBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.LookUpServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentBranchBean extends BranchBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Map<String, Object>> statesList;

	public List<Map<String, Object>> getStatesList()
	{
		return statesList;
	}

	@Override
	protected void handlePostConstruct()
	{
		setCountryId(((Number) FacesUtil.getLoginData().get(EntityConstants.Login.countryId)).shortValue());
		loadStatesList();
		setDialogeHeight(450);
		setDialogeWidth(770);

	}

	@Override
	protected String getUpdatePageName()
	{
		return "branch_details.xhtml";
	}

	@Override
	protected String getDetailsPageName()
	{
		return "branch_details.xhtml";
	}

	public void addAgentBranch()
	{
		Map<String, Object> data = fillDataMap();
		data.put(EntityConstants.Branch.branchId, getBranchId());
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		agentAdminServiceClient.addAgentBranch(data);
	}

	private Map<String, Object> fillDataMap()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Branch.address, getAddress());
		data.put(EntityConstants.Branch.branchName, getBranchName());
		data.put(EntityConstants.Branch.locationLatitude, getLocationLatitude());
		data.put(EntityConstants.Branch.locationLongitude, getLocationLongitude());
		data.put(EntityConstants.Branch.tel, getTel());
		data.put(EntityConstants.Branch.stateId, getStateId());
		data.put(EntityConstants.Branch.countryId, getCountryId());
		return data;
	}

	public void updateAgentBranch()
	{
		Map<String, Object> data = fillDataMap();
		data.put(EntityConstants.Branch.branchId, getBranchId());
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		agentAdminServiceClient.updateAgentBranch(data);
	}

	public void deleteAgentBranch()
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		agentAdminServiceClient.deleteAgentBranch(getBranchId() + "");
	}

	public void getAgentBranches()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Branch.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		data.put(EntityConstants.Branch.countryId, getCountryId());
		data.put(EntityConstants.Branch.stateId, getStateId());
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> agentBranchesList = agentAdminServiceClient.getAgentBranchesList(data);
		populateTable(agentBranchesList, EntityConstants.Branch.branchName, EntityConstants.Branch.address);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> branchData = agentAdminServiceClient.viewBranchDetails(data.get(EntityConstants.Branch.branchId) + "");
		fillDetailsData(branchData);
	}

	public void loadStatesList()
	{
		LookUpServiceClient lookUpServiceClient = ServiceClientUtil.getLookUpServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.State.countryId, getCountryId());
		statesList = lookUpServiceClient.getAllStates(data);
	}

	public void onPointSelect(PointSelectEvent event)
	{
		LatLng latlng = event.getLatLng();
		setLocationLatitude(latlng.getLat());
		setLocationLongitude(latlng.getLng());
	}

	@Override
	protected void resetToAdd()
	{
		setAddress(null);
		setBranchId(null);
		setBranchName(null);
		setCountryId(null);
		setLocationLatitude(null);
		setLocationLongitude(null);
		setStateId(null);
		setTel(null);
	}
}
