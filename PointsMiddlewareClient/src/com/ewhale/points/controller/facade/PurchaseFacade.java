package com.ewhale.points.controller.facade;

import com.ewhale.points.common.exception.FacadeException;

public interface PurchaseFacade extends AbsoluteFacade
{

	boolean isSynchronizationNeeded(long dateInTimeMillis, long profileId) throws FacadeException;

}
