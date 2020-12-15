/**
 * 
 */
package com.ewhale.points.ws.agent;

import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.AgentFacade;
import com.ewhale.points.controller.facade.AgentRateFacade;
import com.ewhale.points.controller.facade.SysPaymentFacade;
import com.ewhale.points.ws.main.ServiceControler;

/**
 * @author Ayman Yosry
 */
public class AgentServiceControler extends ServiceControler
{
	public Map<String, Object> addAgent(AgentFacade facade, Map<String, Object> data) throws FacadeException, ValidationException
	{
		addStatusData(data, EntityConstants.Status.Fixed.pendingStatus.ID);
		return facade.add(data);
	}

	public float getAgentBalance(SysPaymentFacade sysPaymentFacade, Long agentId) throws FacadeException
	{
		return sysPaymentFacade.getAgentBalance(agentId);
	}
	

	public float getAgentRate(AgentRateFacade agentRateFacade, Long agentId) throws FacadeException
	{
		return agentRateFacade.getAgentRate(agentId);
	}
}
