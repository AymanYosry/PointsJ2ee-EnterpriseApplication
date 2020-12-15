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
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.Agent;
import com.ewhale.points.model.entity.Currency;
import com.ewhale.points.model.entity.PaymentMethod;
import com.ewhale.points.model.entity.SysPayment;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class SysPaymentFacadeBean
 */
@Stateless
@Local(SysPaymentFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SysPaymentFacadeBean extends AbsoluteFacadeBean implements SysPaymentFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.SYSPAYMENT) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		data.put(EntityConstants.ItemStatus.updateStatusEmpId, data.get(EntityConstants.ChangeTracking.insEmpId));
		FacadeBeanUtils.addStatusDetails(em, entity, data);
		FacadeBeanUtils.addInsEmpDetails(em, entity, data);
		Agent agent = em.viewRecordDetails(Agent.class, ((Number) data.get(EntityConstants.SysPayment.agentId)).longValue());
		// IMP_Ahmed we have to add the exchange rate
		// IMP_Ayman we have to add the exchange rate
		Currency currency = em.viewRecordDetails(Currency.class, ((Number) data.get(EntityConstants.SysPayment.currencyId)).shortValue());
		PaymentMethod paymentMethod = em.viewRecordDetails(PaymentMethod.class,
				((Number) data.get(EntityConstants.SysPayment.paymentMethodId)).shortValue());
		((SysPayment) entity).setAgent(agent);
		((SysPayment) entity).setCurrency(currency);
		((SysPayment) entity).setPaymentMethod(paymentMethod);
	}

	@Override
	public float getAgentBalance(long agentId) throws FacadeException
	{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(EntityConstants.SysPayment.agentId, agentId);
		try
		{
			float agentSumPaid = 0;
			float agentSumInvoice = 0;
			@SuppressWarnings("rawtypes")
			List agentSumPaidList = em.executeNamedQuery(EntityConstants.SysPayment.agentSumPaidNamedQuery, parameters);
			Double agentSumPaidDouble = (Double) agentSumPaidList.get(0);
			@SuppressWarnings("rawtypes")
			List agentSumInvoiceList = em.executeNamedQuery(EntityConstants.SysInvoice.agentSumInvoiceNamedQuery, parameters);
			Double agentSumInvoiceDouble = (Double) agentSumInvoiceList.get(0);
			if (agentSumInvoiceDouble != null)
				agentSumInvoice = agentSumInvoiceDouble.floatValue();
			if (agentSumPaidDouble != null)
				agentSumPaid = agentSumPaidDouble.floatValue();
			return (float) (agentSumPaid - agentSumInvoice);
		}
		catch (EntityException e)
		{
			throw new FacadeException(e);
		}
	}

	@Override
	public Map<String, Object> update(Map<String, Object> data) throws FacadeException
	{
		return null;
	}

	@Override
	public int update(Map<String, Object> data, Map<String, Object> criteria, int expectedNoOfUpdatedRecords) throws FacadeException
	{
		return -1;
	}

	@Override
	public Map<String, Object> delete(Number... id) throws FacadeException
	{
		return null;
	}

}
