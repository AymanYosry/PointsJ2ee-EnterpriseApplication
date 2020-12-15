package com.ewhale.points.web.managedbean.systemadmin;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.ProfileBean;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.system.proxy.SystemAdminServiceClient;

@SessionScoped
@ManagedBean
public class SysAdminUserBean extends ProfileBean
{

	private Date registrationDate_From;

	private Date registrationDate_To;

	private String fullNameSe;

	private int userPoints;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SystemAdminServiceClient systemAdminServiceClient = ServiceClientUtil.getSystemAdminServiceClient(FacesUtil.getLoginToken());

	public Date getRegistrationDate_From()
	{
		return registrationDate_From;
	}

	public void setRegistrationDate_From(Date registrationDate_From)
	{
		this.registrationDate_From = registrationDate_From;
	}

	public Date getRegistrationDate_To()
	{
		return registrationDate_To;
	}

	public void setRegistrationDate_To(Date registrationDate_To)
	{
		this.registrationDate_To = registrationDate_To;
	}

	public String getFullNameSe()
	{
		return fullNameSe;
	}

	public void setFullNameSe(String fullNameSe)
	{
		this.fullNameSe = fullNameSe;
	}

	public int getUserPoints()
	{
		return userPoints;
	}

	public void setUserPoints(int userPoints)
	{
		this.userPoints = userPoints;
	}

	@Override
	protected void handlePostConstruct()
	{
		setCanAdd(false);
		setCanUpdate(false);
	}

	public void activateStatus()
	{
		changeStatus(EntityConstants.Status.Fixed.activeStatus.ID);
		getUsersList();
		closeDialoge();
	}

	public void blockStatus()
	{
		changeStatus(EntityConstants.Status.Fixed.blockedStatus.ID);
		getUsersList();
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

	public void getUsersList()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Profile.insertDate_From_Search, registrationDate_From);
		data.put(EntityConstants.Profile.insertDate_To_Search, registrationDate_To);
		data.put(EntityConstants.Profile.fullName, fullNameSe);
		data.put(EntityConstants.Profile.mobile, getMobile());
		List<Map<String, Object>> allUsersList = systemAdminServiceClient.getProfilesList(data);
		String[] linkableLists = new String[]
			{ EntityConstants.Profile.points };
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Profile.fullName, null },
					{ EntityConstants.Profile.insertDate, null },
					// { EntityConstants.Profile.mobile, null },
					{ EntityConstants.Profile.role, EntityConstants.Role.roleName },
					{ EntityConstants.Profile.statusName, null } };
		populateTable(allUsersList, linkableLists, columnKeys);
	}

	@Override
	public String viewListDetails(Map<String, Object> data, String selectedField)
	{
		long selectedProfileId = ((Number) data.get(EntityConstants.Profile.profileId)).longValue();
		String selectedProfileFullName = (String) data.get(EntityConstants.Profile.fullName);
		if (selectedField.equals(EntityConstants.Profile.points))
		{
			SysAdminPointBean sysAdminPointBean = FacesUtil.getObjectFromSession(SysAdminPointBean.class, true);
			sysAdminPointBean.setProfileId(selectedProfileId);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -3);
			sysAdminPointBean.setPointDate_from(calendar.getTime());
			sysAdminPointBean.setParentPageTitle(selectedProfileFullName);
			sysAdminPointBean.setParentPage("profile_search");
			sysAdminPointBean.userPointsList();
			return "point_search";
		}
		return null;
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		Map<String, Object> userData = systemAdminServiceClient.profileDetails(data.get(EntityConstants.Profile.profileId) + "");
		userPoints = systemAdminServiceClient.getSumProfilePoints(data.get(EntityConstants.Profile.profileId) + "");
		fillDetailsData(userData);
	}

	public void deleteUser()
	{
		systemAdminServiceClient.deleteProfile(getProfileId() + "");
	}

	@Override
	protected String getDetailsPageName()
	{
		return "profile_details.xhtml";
	}
}
