package com.ewhale.points.controller.facade;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.StateFacade;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.Country;
import com.ewhale.points.model.entity.State;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class StateFacadeBean
 */
@Stateless
@Local(StateFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StateFacadeBean extends AbsoluteFacadeBean implements StateFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.STATE) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		Country country = em.viewRecordDetails(Country.class, ((Number) data.get(EntityConstants.State.countryId)).byteValue());
		((State) entity).setCountry(country);
	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
		data.remove(EntityConstants.State.countryId);
	}
}
