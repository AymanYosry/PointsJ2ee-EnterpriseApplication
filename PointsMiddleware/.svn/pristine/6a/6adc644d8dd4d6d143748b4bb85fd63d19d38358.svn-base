package com.ewhale.points.controller.facade;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class MessageCenterFacadeBean
 */
@Stateless
@Local(MessageCenterFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MessageCenterFacadeBean extends AbsoluteFacadeBean implements MessageCenterFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.MESSAGECENTER) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		// MessageCenter messageCenter = (MessageCenter) entity;
		// Agent agent = em.viewRecordDetails(Agent.class, ((Number) data.get(EntityConstants.MessageCenter.agentId)).longValue());
		// messageCenter.setAgent(agent);
		//
		// Profile requestAgentEmp = em.viewRecordDetails(Profile.class,
		// ((Number) data.get(EntityConstants.MessageCenter.requestAgentEmpId)).longValue());
		// messageCenter.setRequestAgentEmp(requestAgentEmp);
		//
		// FunctionType functionType = em.viewRecordDetails(FunctionType.class,
		// ((Number) data.get(EntityConstants.MessageCenter.functionTypeId)).shortValue());
		// messageCenter.setFunctionType(functionType);

	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
	}
}
