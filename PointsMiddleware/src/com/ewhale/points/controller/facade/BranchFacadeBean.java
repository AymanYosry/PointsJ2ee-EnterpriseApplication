package com.ewhale.points.controller.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.Agent;
import com.ewhale.points.model.entity.Branch;
import com.ewhale.points.model.entity.Country;
import com.ewhale.points.model.entity.State;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class BranchFacadeBean
 */
@Stateless
@Local(BranchFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BranchFacadeBean extends AbsoluteFacadeBean implements BranchFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.BRANCH) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		Agent agent = em.viewRecordDetails(Agent.class, ((Number) data.get(EntityConstants.Branch.agentId)).longValue());
		State state = em.viewRecordDetails(State.class, ((Number) data.get(EntityConstants.Branch.stateId)).intValue());
		Country country = em.viewRecordDetails(Country.class, ((Number) data.get(EntityConstants.Branch.countryId)).byteValue());
		((Branch) entity).setAgent(agent);
		((Branch) entity).setState(state);
		((Branch) entity).setCountry(country);
	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
		Number stateIdObj = ((Number) data.remove(EntityConstants.Branch.stateId));
		State state = new State();
		state.setStateId(stateIdObj.intValue());
		data.put(EntityConstants.Branch.state, state);

		Number countryIdObj = ((Number) data.remove(EntityConstants.Branch.countryId));
		Country country = new Country();
		country.setCountryId(countryIdObj.byteValue());
		data.put(EntityConstants.Branch.country, country);

		Number agentIdObj = ((Number) data.remove(EntityConstants.Branch.agentId));
		Agent agent = new Agent();
		agent.setEntityId(agentIdObj.longValue());
		data.put(EntityConstants.Branch.agent, agent);
	}
	
	@Override
	public boolean isSynchronizationNeeded(long dateInTimeMillis) throws FacadeException
	{
		try
		{
			long lastUpdateTimeInMillis = 0;
			@SuppressWarnings("rawtypes")
			List lastUpdateDateList = em.executeNamedQuery(Agent.lastUpdateDateNamedQuery, null,1);
			Date lastUpdateDate = (Date) lastUpdateDateList.get(0);
			if (lastUpdateDate != null)
				 lastUpdateTimeInMillis = lastUpdateDate.getTime();
			return  lastUpdateTimeInMillis>dateInTimeMillis;
		}
		catch (EntityException e)
		{
			throw new FacadeException(e);
		}
	}
}
