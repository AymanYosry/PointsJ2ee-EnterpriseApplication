/**
 * 
 */
package com.ewhale.points.ws.system;

import java.util.List;
import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.AgentFacade;
import com.ewhale.points.controller.facade.PointFacade;
import com.ewhale.points.ws.main.ServiceControler;

/**
 * @author Ayman Yosry
 */
public class SystemServiceControler extends ServiceControler
{
	public Map<String, Object> addAgent(AgentFacade facade, Map<String, Object> data) throws FacadeException, ValidationException
	{
		addStatusData(data, EntityConstants.Status.Fixed.activeStatus.ID);
		return facade.add(data);
	}
	
	public int getSumProfilePoints(PointFacade facade, long id) throws FacadeException
	{
		return facade.getSumProfilePoints(id);
	}
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

}
