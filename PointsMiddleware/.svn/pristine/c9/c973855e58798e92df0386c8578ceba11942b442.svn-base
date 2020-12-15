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
import com.ewhale.points.controller.facade.CountryFacade;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.Country;
import com.ewhale.points.model.entity.Currency;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class CountryFacadeBean
 */
@Stateless
@Local(CountryFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CountryFacadeBean extends AbsoluteFacadeBean implements CountryFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.COUNTRY) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		Currency currency = em.viewRecordDetails(Currency.class, ((Number) data.get(EntityConstants.Country.currencyId)).shortValue());
		((Country) entity).setCurrency(currency);

	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
		Currency currency = new Currency();
		currency.setCurrencyId(((Number) data.remove(EntityConstants.Country.currencyId)).shortValue());
		data.put(EntityConstants.Country.currency, currency);
	}
}
