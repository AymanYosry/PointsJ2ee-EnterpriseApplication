package com.ewhale.points.controller.facade;

import com.ewhale.points.common.exception.FacadeException;

public interface AgentFacade  extends AbsoluteFacade{

	public boolean isSynchronizationNeeded(long dateInTimeMillis) throws FacadeException;
}
