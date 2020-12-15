package com.ewhale.points.web.managedbean.agentadmin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.ProfileBean;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.LookUpServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentEmployeeBean extends ProfileBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());

	private List<Map<String, Object>> rolesList;

	public List<Map<String, Object>> getRolesList()
	{
		return rolesList;
	}

	@Override
	protected void handlePostConstruct()
	{
		loadRolesList();
	}

	private void loadRolesList()
	{
		LookUpServiceClient lookUpServiceClient = ServiceClientUtil.getLookUpServiceClient(FacesUtil.getLoginToken());
		rolesList = lookUpServiceClient.getAllRoles();
		for (Iterator<Map<String, Object>> iterator = rolesList.iterator(); iterator.hasNext();)
		{
			Map<String, Object> role = iterator.next();
			int roleId = ((Number) role.get(EntityConstants.Role.roleId)).intValue();
			if (roleId != EntityConstants.Role.Fixed.agentAdminRole.ID && roleId != EntityConstants.Role.Fixed.agentSellerRole.ID)
				iterator.remove();
		}
	}

	public void addAgentEmployee()
	{
		Map<String, Object> data = fillInsertDataMap();
		data.put(EntityConstants.Profile.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		data.put(EntityConstants.Profile.updateStatusEmpId, FacesUtil.getLoginId());
		agentAdminServiceClient.addAgentEmployee(data);
		FacesUtil.growlInfoMessage("Success", "Employee Added successfully");
	}

	public void updateAgentEmployeeRole()
	{
		agentAdminServiceClient.updateAgentEmployeeRole("" + getProfileId(), "" + getRoleId());
		FacesUtil.growlInfoMessage("Success", "Employee Updated successfully");
	}

	public void getAgentEmployeesList()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.MessageCenter.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		List<Map<String, Object>> agentEmployeesList = agentAdminServiceClient.getAgentEmploeesList(data);

		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Profile.firstName, null },
					{ EntityConstants.Profile.lastName, null },
					{ EntityConstants.Profile.mobile, null },
					{ EntityConstants.Profile.role, EntityConstants.Role.roleName },
					{ EntityConstants.Profile.status, EntityConstants.Status.statusName } };

		populateTable(agentEmployeesList, columnKeys);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> employeeData = agentAdminServiceClient.employeeDetails(data.get(EntityConstants.Profile.profileId) + "");
		fillDetailsData(employeeData);
	}

	@Override
	public Map<String, Object> fillUpdateDataMap()
	{
		Map<String, Object> data = super.fillUpdateDataMap();
		data.put(EntityConstants.Profile.roleId, getRoleId());
		return data;
	}

	@Override
	protected String getUpdatePageName()
	{
		return "employee_details.xhtml";
	}

	@Override
	protected String getDetailsPageName()
	{
		return "employee_details.xhtml";
	}

	public void deleteAgentEmployee()
	{
		agentAdminServiceClient.deleteAgentEmployee(getProfileId() + "");
	}
}
