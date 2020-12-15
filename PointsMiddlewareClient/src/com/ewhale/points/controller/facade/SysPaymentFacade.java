package com.ewhale.points.controller.facade;

import com.ewhale.points.common.exception.FacadeException;

public interface SysPaymentFacade extends AbsoluteFacade
{

	public float getAgentBalance(long agentId) throws FacadeException;
}
