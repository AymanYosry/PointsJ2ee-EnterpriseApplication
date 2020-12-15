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
public class RoleBean extends AbsoluteBean
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer roleId;

	private String roleName;

	public RoleBean()
	{
		setHasMoreDetails(false);
		setCanAdd(false);
	}

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	@Override
	protected String getDetailsPageName()
	{
		return null; // has no more details
	}
	
	@Override
	protected String getUpdatePageName()
	{
		return "role_details.xhtml";
	}
	
	@Override
	protected void handlePostConstruct()
	{
		viewRolesList();
	}
	
	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		roleId = ((Number)data.get(EntityConstants.Role.roleId)).intValue();
		roleName = (String)data.get(EntityConstants.Role.roleName);
	}
	
	public void viewRolesList()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> allRolesCollection = systemSalesServiceClient.getAllRoles();
		populateTable(allRolesCollection, EntityConstants.Role.roleId, EntityConstants.Role.roleName);
		// LOG.debug("role size :" + allRolesCollection.size());
	}

	public void addRole()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Role.roleName, roleName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.addRole(data);

	}

	public void updateRole()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Role.roleId, roleId);
		data.put(EntityConstants.Role.roleName, roleName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.updateRole(data);

	}

	public void deleteRole()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.deleteRole(roleId + "");

	}
	@Override
	protected void resetToAdd()
	{
		setRoleId(null);
		setRoleName(null);
	}

}
