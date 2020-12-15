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
import com.ewhale.points.controller.facade.SysInvoiceFacade;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.Agent;
import com.ewhale.points.model.entity.Currency;
import com.ewhale.points.model.entity.SysInvoice;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class SysInvoiceFacadeBean
 */
@Stateless
@Local(SysInvoiceFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SysInvoiceFacadeBean extends AbsoluteFacadeBean implements SysInvoiceFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.SYSINVOICE) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		Agent agent = em.viewRecordDetails(Agent.class, ((Number) data.get(EntityConstants.SysInvoice.agentId)).longValue());
		Currency currency = em.viewRecordDetails(Currency.class, ((Number) data.get(EntityConstants.SysInvoice.currencyId)).shortValue());
		((SysInvoice) entity).setAgent(agent);
		((SysInvoice) entity).setCurrency(currency);
	}

}
