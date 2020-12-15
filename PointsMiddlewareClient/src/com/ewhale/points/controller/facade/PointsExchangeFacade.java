package com.ewhale.points.controller.facade;

import com.ewhale.points.common.exception.FacadeException;

public interface PointsExchangeFacade extends AbsoluteFacade
{
	public boolean isSynchronizationNeeded(long dateInTimeMillis, long profileId) throws FacadeException;
}
