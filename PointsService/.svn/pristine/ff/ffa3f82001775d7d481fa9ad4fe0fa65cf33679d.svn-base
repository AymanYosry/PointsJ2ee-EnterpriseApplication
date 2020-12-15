package com.ewhale.points.ws.user;

import java.util.List;
import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.PointFacade;
import com.ewhale.points.ws.main.ServiceControler;

public class UserServiceControler extends ServiceControler
{
	public List<Map<String, Object>> userGainedPointsList(PointFacade facade, long id, Map<String, Object> pointsData) throws FacadeException
	{
		pointsData.put(EntityConstants.Point.txnType, 1);
		pointsData.put(EntityConstants.Point.profileId, id);
		List<Map<String, Object>> pointsList = getList(facade, pointsData);
		return pointsList;
	}

	public List<Map<String, Object>> userReleasedPointsList(PointFacade facade, long id, Map<String, Object> pointsData) throws FacadeException
	{
		pointsData.put(EntityConstants.Point.txnType, -1);
		pointsData.put(EntityConstants.Point.profileId, id);
		List<Map<String, Object>> pointsList = getList(facade, pointsData);
		return pointsList;
	}

	public int getSumProfilePoints(PointFacade facade, long id) throws FacadeException
	{
		return facade.getSumProfilePoints(id);
	}

	public void rejectPoints(PointFacade facade, long pointsId, long profileId) throws FacadeException
	{
		facade.rejectPoints(pointsId, profileId);
	}

	public int confirmPoints(PointFacade facade, long pointsId, long profileId) throws FacadeException, ValidationException
	{
		return facade.confirmPoints(pointsId, profileId);
	}
	
	public Map<String, Object> confirmPoints(PointFacade facade, String mobile, String qrCode) throws FacadeException, ValidationException
	{
		return facade.confirmPoints(mobile,qrCode);
	}
}
