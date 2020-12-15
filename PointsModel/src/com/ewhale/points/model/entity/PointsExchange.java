package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the points_exchange database table.
 */
@Entity
@Table(name = "points_exchange")
@NamedQueries(value =
{ @NamedQuery(name = "PointsExchange.findAll", query = "SELECT p FROM PointsExchange p"),
		// IMP_Ahmed the where condition of the user
		@NamedQuery(name = "PointsExchange.lastUpdateDateNamedQuery", query = "SELECT MAX(p.insertDate) FROM PointsExchange p join p.profile prf where prf.profileId=:profileId ") })
@PrimaryKeyJoinColumn(name = "points_exchange_id", referencedColumnName = "points_id")
public class PointsExchange extends Point implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.PointsExchange.pointsExchangeId };
	
	public static final String lastUpdateDateNamedQuery = "PointsExchange.lastUpdateDateNamedQuery";

	@Column(name = "points_exchange_id", unique = true, nullable = false, insertable = false, updatable = false)
	private long pointsExchangeId;

	@Column(name = "price_value", nullable = false)
	private float priceValue;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "points_exchange_agent_id", nullable = false)
	private Agent agent;

	// bi-directional many-to-one association to Branch
	@ManyToOne
	@JoinColumn(name = "points_exchange_branch_id", nullable = false)
	private Branch branch;

	// bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name = "points_exchange_currency_id", nullable = false)
	private Currency currency;

	// bi-directional many-to-one association to SysInvoice
	@ManyToOne
	@JoinColumn(name = "points_exchange_sys_invoice_id")
	private SysInvoice sysInvoice;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "points_exchange_product_id", nullable = false)
	private Product product;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "points_exchange_profile_id", nullable = false)
	private Profile profile;

	public PointsExchange()
	{
	}

	public long getPointsExchangeId()
	{
		return this.pointsExchangeId;
	}

	public void setPointsExchangeId(long pointsExchangeId)
	{
		this.pointsExchangeId = pointsExchangeId;
	}

	public float getPriceValue()
	{
		return this.priceValue;
	}

	public void setPriceValue(float priceValue)
	{
		this.priceValue = priceValue;
	}

	public void setExchange(boolean exchange)
	{
		setTxnType(exchange ? (byte) -1 : (byte) 1);
	}

	public boolean isExchange()
	{
		return getTxnType() == -1;
	}

	public Agent getAgent()
	{
		return this.agent;
	}

	public void setAgent(Agent agent)
	{
		this.agent = agent;
	}

	public Branch getBranch()
	{
		return this.branch;
	}

	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}

	public Currency getCurrency()
	{
		return this.currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public SysInvoice getSysInvoice()
	{
		return this.sysInvoice;
	}

	public void setSysInvoice(SysInvoice sysInvoice)
	{
		this.sysInvoice = sysInvoice;
	}

	public Product getProduct()
	{
		return this.product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public Profile getProfile()
	{
		return this.profile;
	}

	public void setProfile(Profile profile)
	{
		this.profile = profile;
		setPointsProfile(profile);
	}

	@Override
	public Map<String, Object> transformToMap()
	{
		Map<String, Object> map = transformMainDataToMap();
		return map;
	}

	@Override
	public Map<String, Object> transformMainDataToMap()
	{
		Map<String, Object> map = super.transformMainDataToMap();
		map.put(EntityConstants.PointsExchange.pointsExchangeId, pointsExchangeId);
		map.put(EntityConstants.PointsExchange.priceValue, priceValue);
		map.put(EntityConstants.PointsExchange.pointsValue, getPointsValue() * getTxnType());
		map.put(EntityConstants.PointsExchange.exchange, isExchange());
		map.put(EntityConstants.PointsExchange.txnType, getTxnType());

		map.put(EntityConstants.PointsExchange.profileId, profile.getProfileId());
		map.put(EntityConstants.PointsExchange.profile, profile.transformMainDataToMap());
		map.put(EntityConstants.PointsExchange.productId, product.getProductId());
		map.put(EntityConstants.PointsExchange.product, product.transformMainDataToMap());
		map.put(EntityConstants.PointsExchange.insertDate, getStringFromDate(getInsertDate()));
		map.put(EntityConstants.PointsExchange.branchId, branch.getBranchId());
		map.put(EntityConstants.PointsExchange.branch, branch.transformMainDataToMap());
		map.put(EntityConstants.PointsExchange.agentId, agent.getAgentId());
		map.put(EntityConstants.PointsExchange.agent, agent.transformMainDataToMap());
		map.put(EntityConstants.PointsExchange.invoiceId, sysInvoice.getSysInvoiceId());
		map.put(EntityConstants.PointsExchange.currencyId, currency.getCurrencyId());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);
		if (useId)
			setInsertDate(getDateFromLong((Long) data.get(EntityConstants.PointsExchange.insertDate)));
		setExchange((Boolean) data.get(EntityConstants.PointsExchange.exchange));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot,
			Map<String, Object> criteria) throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Number pointsExchangeId = (Number) criteria.get(EntityConstants.PointsExchange.pointsExchangeId);
		if (pointsExchangeId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.PointsExchange.pointsExchangeId), pointsExchangeId.longValue()));
		Number lastPointsExchangeId = (Number) criteria.get(EntityConstants.PointsExchange.lastPointsExchangeId);
		if (lastPointsExchangeId != null)
			predicateList.add(
					criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.PointsExchange.pointsExchangeId), lastPointsExchangeId.longValue()));

		Number branchId = (Number) criteria.get(EntityConstants.PointsExchange.branchId);
		if (branchId != null)
		{
			Join<PointsExchange, Branch> pointsExchangeBranchJoin = criteriaRoot.join(EntityConstants.PointsExchange.branch);
			predicateList.add(criteriaBuilder.equal(pointsExchangeBranchJoin.get(EntityConstants.Branch.branchId), branchId.longValue()));
		}
		Number agentId = (Number) criteria.get(EntityConstants.PointsExchange.agentId);
		if (agentId != null)
		{
			Join<PointsExchange, Agent> pointsExchangeAgentJoin = criteriaRoot.join(EntityConstants.PointsExchange.agent);
			predicateList.add(criteriaBuilder.equal(pointsExchangeAgentJoin.get(EntityConstants.Agent.agentId), agentId.longValue()));
		}
		String profileMobileNumber = (String) criteria.get(EntityConstants.PointsExchange.profile_mobile);
		if (profileMobileNumber != null)
		{
			Join<PointsExchange, Profile> pointsExchangeProfileJoin = criteriaRoot.join(EntityConstants.PointsExchange.profile);
			predicateList.add(criteriaBuilder.equal(pointsExchangeProfileJoin.get(EntityConstants.Profile.mobile), profileMobileNumber));
		}
		Number profileId = (Number) criteria.get(EntityConstants.PointsExchange.profileId);
		if (profileId != null)
		{
			Join<PointsExchange, Profile> pointsExchangeProfileJoin = criteriaRoot.join(EntityConstants.PointsExchange.profile);
			predicateList.add(criteriaBuilder.equal(pointsExchangeProfileJoin.get(EntityConstants.Profile.profileId), profileId.longValue()));
		}
		Number productId = (Number) criteria.get(EntityConstants.PointsExchange.productId);
		if (productId != null)
		{
			Join<PointsExchange, Product> pointsExchangeProductJoin = criteriaRoot.join(EntityConstants.PointsExchange.product);
			predicateList.add(criteriaBuilder.equal(pointsExchangeProductJoin.get(EntityConstants.Product.productId), productId.longValue()));
		}
		Number invoiceId = (Number) criteria.get(EntityConstants.PointsExchange.invoiceId);
		if (invoiceId != null)
		{
			Join<PointsExchange, SysInvoice> pointsExchangeSysInvoiceJoin = criteriaRoot.join(EntityConstants.PointsExchange.sysInvoice);
			predicateList
					.add(criteriaBuilder.equal(pointsExchangeSysInvoiceJoin.get(EntityConstants.SysInvoice.sysInvoiceId), invoiceId.longValue()));
		}

		Long insertDateFrom = (Long) criteria.get(EntityConstants.PointsExchange.insertDate_From_Search);
		if (insertDateFrom != null)
			predicateList.add(criteriaBuilder.greaterThanOrEqualTo(criteriaRoot.get(EntityConstants.PointsExchange.insertDate),
					getDateFromLong(insertDateFrom)));
		Long insertDateTo = (Long) criteria.get(EntityConstants.PointsExchange.insertDate_To_Search);
		if (insertDateTo != null)
			predicateList.add(
					criteriaBuilder.lessThanOrEqualTo(criteriaRoot.get(EntityConstants.PointsExchange.insertDate), getDateFromLong(insertDateTo)));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		long id = ((Number) data.get(EntityConstants.PointsExchange.pointsExchangeId)).longValue();
		setPointsId(id);
		setPointsExchangeId(id);
	}

	@Override
	public Object getEntityId()
	{
		return getPointsExchangeId();
	}

	@Override
	public <T extends AbsoluteEntity> Order[] getDefaultOrderFields(CriteriaBuilder criteriaBuilder, Root<T> criteriaRoot)
	{
		return new Order[]
			{ criteriaBuilder.desc(criteriaRoot.get(EntityConstants.PointsExchange.insertDate)) };
	}
}
