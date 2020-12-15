package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the payment_method database table.
 */
@Entity
@Table(name = "payment_method")
@NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p")
public class PaymentMethod extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.PaymentMethod.paymentMethodId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_method_id", unique = true, nullable = false)
	private short paymentMethodId;

	@Column(name = "payment_method_name", nullable = false, length = 15)
	private String paymentMethodName;

	// bi-directional many-to-one association to SysPayment
	@OneToMany(mappedBy = "paymentMethod")
	private List<SysPayment> sysPayments;

	public PaymentMethod()
	{
	}

	public short getPaymentMethodId()
	{
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(short paymentMethodId)
	{
		this.paymentMethodId = paymentMethodId;
	}

	public String getPaymentMethodName()
	{
		return this.paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName)
	{
		this.paymentMethodName = paymentMethodName;
	}

	public List<SysPayment> getSysPayments()
	{
		return this.sysPayments;
	}

	public void setSysPayments(List<SysPayment> sysPayments)
	{
		this.sysPayments = sysPayments;
	}

	public SysPayment addSysPayment(SysPayment sysPayment)
	{
		getSysPayments().add(sysPayment);
		sysPayment.setPaymentMethod(this);

		return sysPayment;
	}

	public SysPayment removeSysPayment(SysPayment sysPayment)
	{
		getSysPayments().remove(sysPayment);
		sysPayment.setPaymentMethod(null);

		return sysPayment;
	}
	@Override
	public Map<String, Object> transformToMap()
	{
		Map<String, Object> map =transformMainDataToMap();
		return map;
	}
	@Override
	public Map<String, Object> transformMainDataToMap()
	{
		Map<String, Object> map = new HashMap<>();
		map.put(EntityConstants.PaymentMethod.paymentMethodId, paymentMethodId);
		map.put(EntityConstants.PaymentMethod.paymentMethodName, paymentMethodName);
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setPaymentMethodName((String) data.get(EntityConstants.PaymentMethod.paymentMethodName));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Object paymentMethodIdSe = criteria.get(EntityConstants.PaymentMethod.paymentMethodId);
		if (paymentMethodIdSe != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.PaymentMethod.paymentMethodId), paymentMethodIdSe));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setPaymentMethodId(((Number) data.get(EntityConstants.PaymentMethod.paymentMethodId)).shortValue());
	}

	@Override
	public Object getEntityId()
	{
		return getPaymentMethodId();
	}
}
