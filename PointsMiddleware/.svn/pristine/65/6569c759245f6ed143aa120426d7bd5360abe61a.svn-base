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
import com.ewhale.points.controller.facade.ContractFacade;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.Agent;
import com.ewhale.points.model.entity.Contract;
import com.ewhale.points.model.entity.Country;
import com.ewhale.points.model.entity.Currency;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class ContractFacadeBean
 */
@Stateless
@Local(ContractFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ContractFacadeBean extends AbsoluteFacadeBean implements ContractFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.CONTRACT) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		// to add insEmp Data
		FacadeBeanUtils.addInsEmpDetails(em, entity, data);
		data.put(EntityConstants.ItemStatus.updateStatusEmpId, data.get(EntityConstants.Contract.insEmpId));
		FacadeBeanUtils.addStatusDetails(em, entity, data);
		Agent agent = em.viewRecordDetails(Agent.class, ((Number) data.get(EntityConstants.Contract.agentId)).longValue());
		Country country = em.viewRecordDetails(Country.class, agent.getCountry().getCountryId());
		Currency currency = em.viewRecordDetails(Currency.class, ((Number) data.get(EntityConstants.Contract.currencyId)).shortValue());
		((Contract) entity).setAgent(agent);
		((Contract) entity).setCountry(country);
		((Contract) entity).setCurrency(currency);

	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
		FacadeBeanUtils.addUpEmpDetails(data);
		Number agentId = ((Number) data.remove(EntityConstants.Contract.agentId));
		if (agentId != null)
		{
			Agent agent = em.viewRecordDetails(Agent.class, agentId.longValue());
			data.put(EntityConstants.Contract.agent, agent);
		
			Country country = new Country();
			country.setCountryId(agent.getCountry().getCountryId());
			data.put(EntityConstants.Contract.country, country);
		}
		Number currencyId = ((Number) data.remove(EntityConstants.Contract.currencyId));
		if (currencyId != null)
		{
			Currency currency = new Currency();
			currency.setCurrencyId(currencyId.shortValue());
			data.put(EntityConstants.Contract.currency, currency);
		}
		
		Long startDate = (Long) data.remove(EntityConstants.Contract.startDate);
		if (startDate != null)
			data.put(EntityConstants.Contract.startDate, new Date(startDate));
		
		Long endDate = (Long) data.remove(EntityConstants.Contract.endDate);
		if (endDate != null)
			data.put(EntityConstants.Contract.endDate, new Date(endDate));
	}
}
