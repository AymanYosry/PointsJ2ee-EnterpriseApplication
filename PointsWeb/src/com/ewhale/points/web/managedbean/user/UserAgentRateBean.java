package com.ewhale.points.web.managedbean.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.AbsoluteBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

@SessionScoped
@ManagedBean
public class UserAgentRateBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private byte rateValue;

	private String userComment;

	private long agentId;

	private long profileId;

	public UserAgentRateBean()
	{

	}

	public byte getRateValue()
	{
		return rateValue;
	}

	public void setRateValue(byte rateValue)
	{
		this.rateValue = rateValue;
	}

	public String getUserComment()
	{
		return userComment;
	}

	public void setUserComment(String userComment)
	{
		this.userComment = userComment;
	}

	public long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(long agentId)
	{
		this.agentId = agentId;
	}

	public long getProfileId()
	{
		return profileId;
	}

	public void setProfileId(long profileId)
	{
		this.profileId = profileId;
	}

	public void addAgentRate()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.AgentRate.agentId, agentId);
		data.put(EntityConstants.AgentRate.rateValue, rateValue);
		data.put(EntityConstants.AgentRate.userComment, userComment);
		data.put(EntityConstants.AgentRate.profileId, profileId);
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		userServiceClient.addAgentRate(data);
	}

	@SuppressWarnings("unused")
	public void userAgentRatesList()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.AgentRate.agentId, agentId);
		data.put(EntityConstants.AgentRate.profileId, profileId);
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> agentRatesList = userServiceClient.userAgentRatesList(data);
		// LOG.debug(agentRatesList.size());
	}
}
