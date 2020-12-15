package com.ewhale.points.controller.facade;

import java.util.HashMap;
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
import com.ewhale.points.common.exception.ValidationException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.AgentRateFacade;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.AgentRate;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class AgentRateFacadeBean
 */
@Stateless
@Local(AgentRateFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AgentRateFacadeBean extends AbsoluteFacadeBean implements AgentRateFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.AGENTRATE) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	//IMP_Ahmed should be removed 
	@Override
	public float getAgentRate(long agentId) throws FacadeException
	{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(EntityConstants.AgentRate.agentId, agentId);
		try
		{
			float avgAgentRate = 0;
			@SuppressWarnings("rawtypes")
			List avgAgentRateList = em.executeNamedQuery(AgentRate.avgAgentRateNamedQuery, parameters);
			Double avgAgentRateDouble = (Double) avgAgentRateList.get(0);
			if (avgAgentRateDouble != null)
				avgAgentRate = avgAgentRateDouble.floatValue();
			return (float) avgAgentRate;
		}
		catch (EntityException e)
		{
			throw new FacadeException(e);
		}
	}

	@Override
	protected void postAdd(Map<String, Object> data, AbsoluteEntity entity) throws EntityException, ValidationException
	{
		// update agent_rate in the agent table
		long agentId = ((AgentRate) entity).getAgent().getAgentId();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(EntityConstants.AgentRate.agentId, agentId);
		int rowsUpdated = em.executeNamedNativeUpdate(AgentRate.updateAgentRate, parameters);
		if (rowsUpdated <= 0)
			throw new EntityException("no rows updated - may be wrong agent id =" + agentId);
	}
}
