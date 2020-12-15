package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.model.entity.interfaces.InsertTracking;

/**
 * The persistent class for the sys_payment database table.
 */
@Entity
@Table(name = "sys_payment")
@NamedQueries(value =
	{ @NamedQuery(name = "SysPayment.findAll", query = "SELECT s FROM SysPayment s"),
			@NamedQuery(name = "SysPayment.agentSumPaid", query = "SELECT SUM(s.paymentValue) FROM SysPayment s where s.agent.agentId=:agentId") })
@PrimaryKeyJoinColumn(name = "sys_payment_id", referencedColumnName = "item_status_id")
public class SysPayment extends ItemStatus implements Serializable, InsertTracking
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.SysPayment.sysPaymentId };

	@Column(name = "sys_payment_id", unique = true, nullable = false, insertable = false, updatable = false)
	private long sysPaymentId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_date", nullable = false)
	private Date insertDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date", nullable = false)
	private Date paymentDate;

	@Column(name = "payment_value", nullable = false)
	private float paymentValue;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "sys_payment_agent_id", nullable = false)
	private Agent agent;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "ins_emp_id", nullable = false)
	private Profile insEmp;

	// bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name = "sys_payment_currency_id", nullable = false)
	private Currency currency;

	// bi-directional many-to-one association to PaymentMethod
	@ManyToOne
	@JoinColumn(name = "sys_payment_payment_method_id", nullable = false)
	private PaymentMethod paymentMethod;

	public SysPayment()
	{
	}

	public long getSysPaymentId()
	{
		return this.sysPaymentId;
	}

	public void setSysPaymentId(long sysPaymentId)
	{
		this.sysPaymentId = sysPaymentId;
		setItemId(sysPaymentId);
	}

	public Date getInsertDate()
	{
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public Date getPaymentDate()
	{
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate)
	{
		this.paymentDate = paymentDate;
	}

	public float getPaymentValue()
	{
		return this.paymentValue;
	}

	public void setPaymentValue(float paymentValue)
	{
		this.paymentValue = paymentValue;
	}

	public Agent getAgent()
	{
		return this.agent;
	}

	public void setAgent(Agent agent)
	{
		this.agent = agent;
	}

	public Profile getInsEmp()
	{
		return this.insEmp;
	}

	public void setInsEmp(Profile insEmp)
	{
		this.insEmp = insEmp;
	}

	public Currency getCurrency()
	{
		return this.currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public PaymentMethod getPaymentMethod()
	{
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	@Override
	public Map<String, Object> transformToMap()
	{
		Map<String, Object> map = transformMainDataToMap();
		map.put(EntityConstants.SysPayment.paymentMethod, paymentMethod.transformMainDataToMap());
		map.put(EntityConstants.SysPayment.currency, currency.transformMainDataToMap());
		return map;
	}

	@Override
	public Map<String, Object> transformMainDataToMap()
	{
		Map<String, Object> map = super.transformMainDataToMap();
		map.put(EntityConstants.SysPayment.sysPaymentId, sysPaymentId);
		map.put(EntityConstants.SysPayment.paymentValue, paymentValue);
		map.put(EntityConstants.SysPayment.paymentDate, getStringFromDate(paymentDate));
		map.put(EntityConstants.SysPayment.insertDate, getStringFromDate(insertDate));
		map.put(EntityConstants.SysPayment.agent, agent.transformMainDataToMap());
		// map.put(EntityConstants.SysPayment.insEmp, insEmp.transformToMap());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setPaymentValue(((Number) data.get(EntityConstants.SysPayment.paymentValue)).floatValue());
		setPaymentDate(getDateFromLong((Long) data.get(EntityConstants.SysPayment.paymentDate)));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot,
			Map<String, Object> criteria) throws EntityException
	{
		List<Predicate> predicateList = super.setCriteria(criteriaBuilder, criteriaRoot, criteria);
		Number paymentId = (Number) criteria.get(EntityConstants.SysPayment.sysPaymentId);
		if (paymentId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.SysPayment.sysPaymentId), paymentId.longValue()));

		Number paymentMethodId = (Number) criteria.get(EntityConstants.SysPayment.paymentMethodId);
		if (paymentMethodId != null)
		{
			Join<SysPayment, PaymentMethod> sysPaymentPaymentMethodJoin = criteriaRoot.join(EntityConstants.SysPayment.paymentMethod);
			predicateList.add(criteriaBuilder.equal(sysPaymentPaymentMethodJoin.get(EntityConstants.PaymentMethod.paymentMethodId),
					paymentMethodId.shortValue()));
		}
		Number agentId = (Number) criteria.get(EntityConstants.SysPayment.agentId);
		if (agentId != null)
		{
			Join<SysPayment, Agent> sysPaymentAgentJoin = criteriaRoot.join(EntityConstants.SysPayment.agent);
			predicateList.add(criteriaBuilder.equal(sysPaymentAgentJoin.get(EntityConstants.Agent.agentId), agentId.longValue()));
		}

		Number insEmpId = (Number) criteria.get(EntityConstants.SysPayment.insEmpId);
		if (insEmpId != null)
		{
			Join<SysPayment, Profile> sysPaymentInsEmpJoin = criteriaRoot.join(EntityConstants.SysPayment.insEmp);
			predicateList.add(criteriaBuilder.equal(sysPaymentInsEmpJoin.get(EntityConstants.Profile.profileId), insEmpId.longValue()));
		}

		Long paymentDate_From = (Long) criteria.get(EntityConstants.SysPayment.paymentDate_From_Search);
		if (paymentDate_From != null)
			predicateList
					.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.SysPayment.paymentDate), getDateFromLong(paymentDate_From)));
		Long paymentDate_To = (Long) criteria.get(EntityConstants.SysPayment.paymentDate_To_Search);
		if (paymentDate_To != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.SysPayment.paymentDate), getDateFromLong(paymentDate_To)));
		Long insertDate_From = (Long) criteria.get(EntityConstants.SysPayment.insertDate_From_Search);
		if (insertDate_From != null)
			predicateList.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.SysPayment.insertDate), getDateFromLong(insertDate_From)));
		Long insertDate_To = (Long) criteria.get(EntityConstants.SysPayment.insertDate_To_Search);
		if (insertDate_To != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.SysPayment.insertDate), getDateFromLong(insertDate_To)));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		long id = ((Number) data.get(EntityConstants.Agent.agentId)).longValue();
		setItemId(id);
		setSysPaymentId(id);
	}

	@Override
	public Object getEntityId()
	{
		return getSysPaymentId();
	}

	@Override
	public void setEntityId(Object idObj)
	{
		long id = ((Number) idObj).longValue();
		setItemId(id);
		setSysPaymentId(id);
	}
}
