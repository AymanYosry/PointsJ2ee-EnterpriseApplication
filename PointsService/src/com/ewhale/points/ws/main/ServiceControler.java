/**
 * 
 */
package com.ewhale.points.ws.main;

import java.util.List;
import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.AbsoluteFacade;

/**
 * @author Ayman Yosry
 */
public class ServiceControler
{
	public Map<String, Object> add(AbsoluteFacade facade, Map<String, Object> data) throws FacadeException, ValidationException
	{
		return facade.add(data);
	}

	public Map<String, Object> update(AbsoluteFacade facade, Map<String, Object> data) throws FacadeException, ValidationException
	{
		return facade.update(data);
	}

	public Map<String, Object> delete(AbsoluteFacade facade, Number... id) throws FacadeException
	{
		return facade.delete(id);
	}

	public List<Map<String, Object>> getAll(AbsoluteFacade facade) throws FacadeException
	{
		return facade.viewAll();
	}

	public Map<String, Object> getDetails(AbsoluteFacade facade, Number... id) throws FacadeException
	{
		return facade.viewDetails(id);
	}

	public List<Map<String, Object>> getList(AbsoluteFacade facade, Map<String, Object> data) throws FacadeException
	{
		return facade.viewList(data);
	}

	protected void addStatusData(Map<String, Object> data, short statusId)
	{
		data.put(EntityConstants.ItemStatus.statusId, statusId);
	}
}
