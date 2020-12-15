package com.ewhale.points.controller.facade;

import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;

public interface PointFacade extends AbsoluteFacade
{

	int getSumProfilePoints(long profileId) throws FacadeException;

	void rejectPoints(long pointsId, long profileId) throws FacadeException;

	int confirmPoints(long pointsId, long profileId) throws FacadeException, ValidationException;

	Map<String, Object> confirmPoints(String mobile, String qrCode) throws FacadeException, ValidationException;

}
