package com.ewhale.points.controller.facade;

import com.ewhale.points.common.exception.FacadeException;

public interface AgentRateFacade extends AbsoluteFacade
{

	public float getAgentRate(long agentId) throws FacadeException;
}
