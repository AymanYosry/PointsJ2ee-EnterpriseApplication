package com.ewhale.points.controller.facade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entitymanager.AbsoluteEntityManager;

public abstract class AbsoluteFacadeBean implements AbsoluteFacade
{
	private static final long serialVersionUID = 1L;

	@EJB
	protected AbsoluteEntityManager em;

	@SuppressWarnings("rawtypes")
	protected Class entityClass;

	/**
	 * Add record in DB
	 * 
	 * @param data
	 * @return
	 * @throws FacadeException
	 * @throws ValidationException
	 */
	@Override
	public Map<String, Object> add(Map<String, Object> data) throws FacadeException, ValidationException
	{
		AbsoluteEntity entity = null;
		try
		{
			entity = (AbsoluteEntity) entityClass.newInstance();

			em.begin();
			entity.setEntityData(data);
			addEntityDetails(entity, data);
			entity = em.addRecord(entity);
			postAdd(data, entity);
			em.commit();
			return entity.transformToMap();
		}
		catch (EntityException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e)
		{
			data = null;
			throw new FacadeException(e.getClass().getName() + "==> Facade error", e);
		}
	}

	protected void postAdd(Map<String, Object> data, AbsoluteEntity entity) throws EntityException, ValidationException
	{
	}

	/**
	 * Update record in DB with data where id is valid
	 * 
	 * @param data
	 * @return
	 * @throws FacadeException
	 * @throws ValidationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> update(Map<String, Object> data) throws FacadeException, ValidationException
	{
		try
		{
			em.begin();
			Field getEntityIdNames = entityClass.getField("entityIdNames");
			String[] entityIdNames = (String[]) getEntityIdNames.get(null);

			Map<String, Object> criteria = new HashMap<>();
			updateEntityDetails(data);
			for (String idName : entityIdNames)
			{
				Number id = (Number) data.remove(idName);
				if (id == null)
					throw new FacadeException("Wrong data");
				criteria.put(idName, id);
			}
			int noOfRecordsUpdate = em.updateRecord(entityClass, data, criteria);
			if (noOfRecordsUpdate != 1)
			{
				data = null;
				em.rollback();
				throw new FacadeException("No Rows Updated");
			}
			em.commit();
			return data;
		}
		catch (EntityException | NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException e)
		{
			data = null;
			throw new FacadeException(e.getClass().getName() + "==> Facade error", e);
		}
	}

	/**
	 * Update one record in DB with data where criteria is valid
	 * Update multiple records in DB with data where criteria is valid (Not Used)
	 * 
	 * @param data
	 * @param criteria
	 * @param expectedNoOfUpdatedRecords
	 * @return
	 * @throws FacadeException
	 */
	@SuppressWarnings(
		{ "unchecked" })
	public int update(Map<String, Object> data, Map<String, Object> criteria, int expectedNoOfUpdatedRecords) throws FacadeException
	{
		try
		{
			em.begin();
			int noOfRecordsUpdate = em.updateRecord(entityClass, data, criteria);
			if (noOfRecordsUpdate < 1)
			{
				em.rollback();
				throw new FacadeException("No Rows Updated");
			}
			if (expectedNoOfUpdatedRecords > 0 && noOfRecordsUpdate != expectedNoOfUpdatedRecords)
			{
				em.rollback();
				throw new FacadeException("Number of Rows Updated is " + noOfRecordsUpdate + " , expected " + expectedNoOfUpdatedRecords);
			}
			em.commit();
			return noOfRecordsUpdate;
		}
		catch (EntityException e)
		{
			data = null;
			throw new FacadeException(e.getClass().getName() + "==> Facade error", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> delete(Number... id) throws FacadeException
	{
		AbsoluteEntity record;
		try
		{
			em.begin();
			Object primaryKey = getPrimaryKey(id);
			record = em.deleteRecord(entityClass, primaryKey);
			em.commit();
		}
		catch (EntityException e)
		{
			throw new FacadeException(e.getClass().getName() + "==> Facade error", e);
		}
		if (record != null)
			return record.transformToMap();
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> viewDetails(Number... id) throws FacadeException
	{
		AbsoluteEntity record;
		try
		{
			Object primaryKey = getPrimaryKey(id);
			record = em.viewRecordDetails(entityClass, primaryKey);
		}
		catch (EntityException e)
		{
			throw new FacadeException(e.getClass().getName() + "==> Facade error", e);
		}
		if (record != null)
			return record.transformToMap();
		else
			return null;
	}

	protected Object getPrimaryKey(Number... id)
	{
		return id[0];
	}

	@SuppressWarnings(
		{ "rawtypes", "unchecked" })
	@Override
	public List<Map<String, Object>> viewList(Map<String, Object> criteria) throws FacadeException
	{
		try
		{
			List<Map<String, Object>> listOfMap;
			if (criteria == null || criteria.size() == 0)
			{
				listOfMap = viewAll();
			}
			else
			{
				List list = em.viewRecordList(entityClass, criteria);
				listOfMap = prepareResultsList(list);
			}
			return listOfMap;
		}
		catch (EntityException modelException)
		{
			modelException.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings(
		{ "rawtypes", "unchecked" })
	@Override
	public List<Map<String, Object>> viewList(Map<String, Object> criteria, int maxResult) throws FacadeException
	{
		try
		{
			List<Map<String, Object>> listOfMap;
			if ((criteria == null || criteria.size() == 0) && maxResult <= 0)
			{
				listOfMap = viewAll();
			}
			else
			{
				List list = em.viewRecordList(entityClass, criteria, maxResult);
				listOfMap = prepareResultsList(list);
			}
			return listOfMap;
		}
		catch (EntityException modelException)
		{
			modelException.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings(
		{ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> viewAll() throws FacadeException
	{
		try
		{
			List list = em.viewAllRecords(entityClass);
			List<Map<String, Object>> listOfMap = prepareResultsList(list);
			return listOfMap;
		}
		catch (EntityException modelException)
		{
			modelException.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private List<Map<String, Object>> prepareResultsList(List list)
	{
		List<Map<String, Object>> listOfMap = new ArrayList<Map<String, Object>>();

		for (Iterator iterator = list.iterator(); iterator.hasNext();)
		{
			AbsoluteEntity record = (AbsoluteEntity) iterator.next();
			// IMP_Ahmed send RoleId as argument to transformToMap to be like record.transformToMap(roleId)
			listOfMap.add(record.transformToMap());
		}
		return listOfMap;
	}

	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException, ValidationException
	{
	}

	protected void updateEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
	}

	protected void updateEntityDetails(Map<String, Object> data) throws EntityException, ValidationException
	{
	}

	@SuppressWarnings("rawtypes")
	protected abstract void setEntityClass(Class entityClass);
}
