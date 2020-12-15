package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the sys_invoice database table.
 */
@Entity
@Table(name = "sys_invoice")
@NamedQueries(value =
	{ @NamedQuery(name = "SysInvoice.findAll", query = "SELECT s FROM SysInvoice s"), @NamedQuery(name = "SysInvoice.agentSumInvoice",
			query = "SELECT SUM(i.invoiceValue) FROM SysInvoice i where i.agent.agentId=:agentId and i.active=false") })
public class SysInvoice extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.SysInvoice.sysInvoiceId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sys_invoice_id", unique = true, nullable = false)
	private long sysInvoiceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_date", nullable = false)
	private Date insertDate;

	@Column(name = "invoice_value", nullable = false)
	private float invoiceValue;

	@Column(name = "active", nullable = false)
	private boolean active;

	// bi-directional many-to-one association to PointsExchange
	@OneToMany(mappedBy = "sysInvoice")
	private List<PointsExchange> pointsExchanges;

	// bi-directional many-to-one association to Promotion
	@OneToMany(mappedBy = "sysInvoice")
	private List<Promotion> promotions;

	// bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy = "sysInvoice")
	private List<Purchase> purchases;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "sys_invoice_agent_id", nullable = false)
	private Agent agent;

	// bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name = "invoice_currency_id", nullable = false)
	private Currency currency;

	public SysInvoice()
	{
	}

	public long getSysInvoiceId()
	{
		return this.sysInvoiceId;
	}

	public void setSysInvoiceId(long sysInvoiceId)
	{
		this.sysInvoiceId = sysInvoiceId;
	}

	public Date getInsertDate()
	{
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public float getInvoiceValue()
	{
		return this.invoiceValue;
	}

	public void setInvoiceValue(float invoiceValue)
	{
		this.invoiceValue = invoiceValue;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public List<PointsExchange> getPointsExchanges()
	{
		return this.pointsExchanges;
	}

	public void setPointsExchanges(List<PointsExchange> pointsExchanges)
	{
		this.pointsExchanges = pointsExchanges;
	}

	public PointsExchange addPointsExchange(PointsExchange pointsExchange)
	{
		getPointsExchanges().add(pointsExchange);
		pointsExchange.setSysInvoice(this);

		return pointsExchange;
	}

	public PointsExchange removePointsExchange(PointsExchange pointsExchange)
	{
		getPointsExchanges().remove(pointsExchange);
		pointsExchange.setSysInvoice(null);

		return pointsExchange;
	}

	public List<Promotion> getPromotions()
	{
		return this.promotions;
	}

	public void setPromotions(List<Promotion> promotions)
	{
		this.promotions = promotions;
	}

	public Promotion addPromotion(Promotion promotion)
	{
		getPromotions().add(promotion);
		promotion.setSysInvoice(this);

		return promotion;
	}

	public Promotion removePromotion(Promotion promotion)
	{
		getPromotions().remove(promotion);
		promotion.setSysInvoice(null);

		return promotion;
	}

	public List<Purchase> getPurchases()
	{
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases)
	{
		this.purchases = purchases;
	}

	public Purchase addPurchase(Purchase purchase)
	{
		getPurchases().add(purchase);
		purchase.setSysInvoice(this);

		return purchase;
	}

	public Purchase removePurchase(Purchase purchase)
	{
		getPurchases().remove(purchase);
		purchase.setSysInvoice(null);

		return purchase;
	}

	public Agent getAgent()
	{
		return this.agent;
	}

	public void setAgent(Agent agent)
	{
		this.agent = agent;
	}

	public Currency getCurrency()
	{
		return this.currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	@Override
	public Map<String, Object> transformToMap()
	{
		Map<String, Object> map = transformMainDataToMap();
		map.put(EntityConstants.SysInvoice.agent, agent.transformMainDataToMap());
		map.put(EntityConstants.SysInvoice.currency, currency.transformMainDataToMap());
		return map;
	}

	@Override
	public Map<String, Object> transformMainDataToMap()
	{
		Map<String, Object> map = new HashMap<>();
		map.put(EntityConstants.SysInvoice.sysInvoiceId, sysInvoiceId);
		map.put(EntityConstants.SysInvoice.invoiceValue, invoiceValue);
		map.put(EntityConstants.SysInvoice.insertDate, getStringFromDate(insertDate));
		map.put(EntityConstants.SysInvoice.active, active);
		map.put(EntityConstants.SysInvoice.agentId, agent.getAgentId());
		map.put(EntityConstants.SysInvoice.currencyId, currency.getCurrencyId());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setInvoiceValue(((Number) data.get(EntityConstants.SysInvoice.invoiceValue)).floatValue());
		setActive(((Boolean) data.get(EntityConstants.SysInvoice.invoiceValue)));
		setInsertDate(getDateFromLong((Long) data.get(EntityConstants.SysInvoice.insertDate)));
		// Agent agent = new Agent();
		// agent.setAgentId(((Number) data.get(EntityConstants.SysInvoice.agentId)).longValue());
		// setAgent(agent);
		// Currency currency = new Currency();
		// currency.setCurrencyId(((Number) data.get(EntityConstants.SysInvoice.currencyId)).shortValue());
		// setCurrency(currency);
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot,
			Map<String, Object> criteria) throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Number invoiceId = (Number) criteria.get(EntityConstants.SysInvoice.sysInvoiceId);
		if (invoiceId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.SysInvoice.sysInvoiceId), invoiceId.longValue()));

		Number agentId = (Number) criteria.get(EntityConstants.SysInvoice.agentId);
		if (agentId != null)
		{
			Join<SysInvoice, Agent> sysInvoiceAgentJoin = criteriaRoot.join(EntityConstants.SysInvoice.agent);
			predicateList.add(criteriaBuilder.equal(sysInvoiceAgentJoin.get(EntityConstants.Agent.agentId), agentId.longValue()));
		}
		Long invoiceDate_From = (Long) criteria.get(EntityConstants.SysInvoice.invoiceDate_From_Search);
		if (invoiceDate_From != null)
			predicateList
					.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.SysInvoice.insertDate), getDateFromLong(invoiceDate_From)));
		Long invoiceDate_To = (Long) criteria.get(EntityConstants.SysInvoice.invoiceDate_To_Search);
		if (invoiceDate_To != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.SysInvoice.insertDate), getDateFromLong(invoiceDate_To)));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setSysInvoiceId(((Number) data.get(EntityConstants.SysInvoice.sysInvoiceId)).longValue());
	}

	@Override
	public Object getEntityId()
	{
		return getSysInvoiceId();
	}
}
