package com.ewhale.points.web.managedbean.systemadmin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.ProfileBean;
import com.ewhale.points.ws.main.proxy.LookUpServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.system.proxy.SystemAdminServiceClient;

@SessionScoped
@ManagedBean
public class SysAdminEmployeeBean extends ProfileBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SystemAdminServiceClient systemAdminServiceClient = ServiceClientUtil.getSystemAdminServiceClient(FacesUtil.getLoginToken());

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
			if (roleId != EntityConstants.Role.Fixed.systemAdminRole.ID && roleId != EntityConstants.Role.Fixed.systemSalesRole.ID)
				iterator.remove();
		}
	}

	public void addEmployee()
	{
		Map<String, Object> data = fillInsertDataMap();
		data.put(EntityConstants.ItemStatus.updateStatusEmpId, FacesUtil.getLoginId());
		systemAdminServiceClient.addSysSalesEmplyee(data);
		FacesUtil.growlInfoMessage("Success", "Employee Added successfully");
	}

	public void updateEmployeeRole()
	{
		systemAdminServiceClient.updateSysEmployeeRole("" + getProfileId(), "" + getRoleId());
		FacesUtil.growlInfoMessage("Success", "Employee Updated successfully");
	}

	public void activateStatus()
	{
		changeStatus(EntityConstants.Status.Fixed.activeStatus.ID);
		getSysEmployeesList();
		closeDialoge();
	}

	public void blockStatus()
	{
		changeStatus(EntityConstants.Status.Fixed.blockedStatus.ID);
		getSysEmployeesList();
		closeDialoge();
	}

	private void changeStatus(int statusId)
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.ItemStatus.itemId, getProfileId());
		data.put(EntityConstants.ItemStatus.statusId, statusId);
		data.put(EntityConstants.ItemStatus.updateStatusEmpId, FacesUtil.getLoginId());
		systemAdminServiceClient.updateItemStatus(data);
		FacesUtil.growlInfoMessage("Success", "Status Has Been Changed successfully");
	}

	public void getSysEmployeesList()
	{
		Map<String, Object> data = new HashMap<>();
//		data.put(EntityConstants.Profile.roleId, getRoleId());
		List<Map<String, Object>> allSysEmployeesList = systemAdminServiceClient.getEmployeeList(data);

		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Profile.firstName, null },
					{ EntityConstants.Profile.lastName, null },
					{ EntityConstants.Profile.mobile, null },
					{ EntityConstants.Profile.role, EntityConstants.Role.roleName },
					{ EntityConstants.Profile.status, EntityConstants.Status.statusName } };
		populateTable(allSysEmployeesList, columnKeys);

	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		Map<String, Object> employeeData = systemAdminServiceClient.profileDetails(data.get(EntityConstants.Profile.profileId) + "");
		fillDetailsData(employeeData);
	}

	public void deleteSysEmployee()
	{
		systemAdminServiceClient.deleteProfile(getProfileId() + "");
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

}
