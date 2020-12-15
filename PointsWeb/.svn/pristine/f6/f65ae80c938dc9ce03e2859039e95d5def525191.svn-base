package com.ewhale.points.web.managedbean.systemadmin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.system.proxy.SystemAdminServiceClient;

public class SysAdminBeanUtils
{
	private static Calendar lastAgentUpdateDate = Calendar.getInstance();

	private static final int agentRefreshRate = 30;

	private static List<Map<String, Object>> allAgentsList;

	static
	{
		loadAgentsList();
	}

	public static List<Map<String, Object>> getAllAgentsList()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1 * agentRefreshRate);
		if (lastAgentUpdateDate.getTimeInMillis() < cal.getTimeInMillis())
			loadAgentsList();
		return allAgentsList;
	}

	private static void loadAgentsList()
	{
		Map<String, Object> data = new HashMap<>();
		SystemAdminServiceClient systemAdminServiceClient = ServiceClientUtil.getSystemAdminServiceClient(FacesUtil.getLoginToken());
		allAgentsList = systemAdminServiceClient.getAgentsList(data);
	}
}
