package com.ewhale.points.controller.facade;

import java.util.Date;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.SysEventFacade;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class SysEventFacadeBean
 */
@Stateless
@Local(SysEventFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SysEventFacadeBean extends AbsoluteFacadeBean implements SysEventFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.SYSEVENT) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		FacadeBeanUtils.addInsEmpDetails(em,entity, data);
	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
		FacadeBeanUtils.addUpEmpDetails(data);
		Date eventDate = AbsoluteEntity.getDateFromLong((((Number) data.get(EntityConstants.SysEvent.eventDate)).longValue()));
		data.put(EntityConstants.SysEvent.eventDate, eventDate);
	}

}
