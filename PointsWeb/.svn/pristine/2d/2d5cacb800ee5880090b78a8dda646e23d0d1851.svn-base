package com.ewhale.points.web.managedbean.user;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

public class UserBeanUtils
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
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		allAgentsList = userServiceClient.agentsList(data);
		lastAgentUpdateDate = Calendar.getInstance();
	}
}
