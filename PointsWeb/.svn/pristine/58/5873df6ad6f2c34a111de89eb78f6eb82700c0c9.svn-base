/**
 * 
 */
package com.ewhale.points.web.managedbean.agentadmin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.web.managedbean.main.AbsoluteBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

/**
 * @author Ahmed Khalil
 */
@SessionScoped
@ManagedBean
public class AgentAdminHomeBean extends AbsoluteBean
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private float agentBalance = 0;

	private Integer agentRate ;

	public Integer getAgentRate()
	{
		return agentRate;
		
	}

	public float getAgentBalance()
	{
		return agentBalance;
	}

	@Override
	protected void handlePostConstruct()
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		agentBalance = agentAdminServiceClient.viewAgentBalance("" + FacesUtil.getLoginAgentId());
		agentRate = (int) agentAdminServiceClient.viewAgentRate("" + FacesUtil.getLoginAgentId());
	}
}
