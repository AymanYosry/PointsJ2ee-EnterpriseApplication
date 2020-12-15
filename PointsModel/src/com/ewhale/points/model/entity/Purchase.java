package com.ewhale.points.model.entity;

import java.io.Serializable;
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
 * The persistent class for the purchase database table.
 */
@Entity
@Table(name = "purchase")
@NamedQueries(value =
	{ @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
			// IMP_Ahmed the where condition of the user
			@NamedQuery(name = "Purchase.lastUpdateDateNamedQuery",
					query = "SELECT MAX(p.insertDate) FROM Purchase p join p.profile prf where prf.profileId=:profileId ") })

@PrimaryKeyJoinColumn(name = "purchase_id", referencedColumnName = "points_id")
public class Purchase extends Point implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String lastUpdateDateNamedQuery = "Purchase.lastUpdateDateNamedQuery";

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Purchase.purchaseId };

	@Column(name = "purchase_id", unique = true, nullable = false, insertable = false, updatable = false)
	private long purchaseId;

	@Column(name = "agent_invoice_number", nullable = false)
	private long agentInvoiceNumber;

	@Column(name = "agent_invoice_value", nullable = false)
	private float agentInvoiceValue;

	@Column(name = "disc_percent", nullable = false)
	private float discPercent;

	@Column(name = "profit_value", nullable = false)
	private float profitValue;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "purchase_agent_id", nullable = false)
	private Agent agent;

	// bi-directional many-to-one association to Branch
	@ManyToOne
	@JoinColumn(name = "purchase_branch_id", nullable = false)
	private Branch branch;

	// bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name = "purchase_currency_id", nullable = false)
	private Currency currency;

	// bi-directional many-to-one association to SysInvoice
	@ManyToOne
	@JoinColumn(name = "purchase_invoice_id", nullable = false)
	private SysInvoice sysInvoice;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "purchase_profile_id", nullable = false)
	private Profile profile;

	public Purchase()
	{
	}

	public long getPurchaseId()
	{
		return this.purchaseId;
	}

	public void setPurchaseId(long purchaseId)
	{
		this.purchaseId = purchaseId;
	}

	public long getAgentInvoiceNumber()
	{
		return this.agentInvoiceNumber;
	}

	public void setAgentInvoiceNumber(long agentInvoiceNumber)
	{
		this.agentInvoiceNumber = agentInvoiceNumber;
	}

	public float getAgentInvoiceValue()
	{
		return this.agentInvoiceValue;
	}

	public void setAgentInvoiceValue(float agentInvoiceValue)
	{
		this.agentInvoiceValue = agentInvoiceValue;
	}

	public float getDiscPercent()
	{
		return this.discPercent;
	}

	public void setDiscPercent(float discPercent)
	{
		this.discPercent = discPercent;
	}

	public float getProfitValue()
	{
		return this.profitValue;
	}

	public void setProfitValue(float profitValue)
	{
		this.profitValue = profitValue;
	}

	public void setFund(boolean fund)
	{
		setTxnType(fund ? (byte) 1 : (byte) -1);
	}

	public boolean isFund()
	{
		return getTxnType() == 1;
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

	public Profile getProfile()
	{
		return this.profile;
	}

	public void setProfile(Profile profile)
	{
		this.profile = profile;
		setPointsProfile(profile);
	}

	private String getQrCode()
	{
		// after insert
		String qrData = profile.getMobile() + "##" + getEntityId() + "##" + agentInvoiceNumber + "##" + agentInvoiceValue;
		return encodeQRData(qrData);
	}

	private String encodeQRData(String qrData)
	{
		// IMP_Ayman Implementation needed here -- reversible encoding needed
		String encodedQRData = qrData;
		return encodedQRData;
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
		map.put(EntityConstants.Purchase.purchaseId, getEntityId());
		map.put(EntityConstants.Purchase.agentInvoiceNumber, agentInvoiceNumber);
		map.put(EntityConstants.Purchase.agentInvoiceValue, getTxnType() * agentInvoiceValue);
		map.put(EntityConstants.Purchase.discPercent, discPercent);
		map.put(EntityConstants.Purchase.pointsValue, getTxnType() * getPointsValue());
		map.put(EntityConstants.Purchase.profitValue, getTxnType() * profitValue);
		map.put(EntityConstants.Purchase.insertDate, getStringFromDate(getInsertDate()));
		map.put(EntityConstants.Purchase.txnType, getTxnType());
		map.put(EntityConstants.Purchase.fund, isFund());
		map.put(EntityConstants.Purchase.insEmpId, getInsEmp().getProfileId());
		map.put(EntityConstants.Purchase.profileId, profile.getProfileId());
		map.put(EntityConstants.Purchase.profile, profile.transformMainDataToMap());
		map.put(EntityConstants.Purchase.agentId, agent.getAgentId());
		map.put(EntityConstants.Purchase.agent, agent.transformMainDataToMap());
		map.put(EntityConstants.Purchase.invoiceId, sysInvoice.getSysInvoiceId());
		map.put(EntityConstants.Purchase.branchId, branch.getBranchId());
		map.put(EntityConstants.Purchase.branch, branch.transformMainDataToMap());
		map.put(EntityConstants.Purchase.currencyId, currency.getCurrencyId());
		map.put(EntityConstants.Purchase.qrCode, getQrCode());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);
		if (useId)
			setInsertDate(getDateFromLong((Long) data.get(EntityConstants.Purchase.insertDate)));

		setAgentInvoiceNumber(((Number) data.get(EntityConstants.Purchase.agentInvoiceNumber)).longValue());
		setAgentInvoiceValue(((Number) data.get(EntityConstants.Purchase.agentInvoiceValue)).floatValue());
		// setDiscPercent(((Number) data.get(EntityConstants.Purchase.discPercent)).floatValue());
		// setProfitValue(((Number) data.get(EntityConstants.Purchase.profitValue)).floatValue());
		setFund((Boolean) data.get(EntityConstants.Purchase.fund));
		// Profile insEmp = new Profile();
		// insEmp.setProfileId(((Number) data.get(EntityConstants.Purchase.insEmpId)).longValue());
		// setInsEmp(insEmp);
		// Profile profile = new Profile();
		// profile.setProfileId(((Number) data.get(EntityConstants.Purchase.profileId)).longValue());
		// setProfile(profile);
		// Agent agent = new Agent();
		// agent.setAgentId(((Number) data.get(EntityConstants.Purchase.agentId)).longValue());
		// setAgent(agent);
		// SysInvoice sysInvoice = new SysInvoice();
		// sysInvoice.setSysInvoiceId(((Number) data.get(EntityConstants.Purchase.invoiceId)).longValue());
		// setSysInvoice(sysInvoice);
		// Branch branch = new Branch();
		// branch.setBranchId(((Number) data.get(EntityConstants.Purchase.branchId)).longValue());
		// setBranch(branch);
		// Currency currency = new Currency();
		// currency.setCurrencyId(((Number) data.get(EntityConstants.Purchase.currencyId)).shortValue());
		// setCurrency(currency);
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot,
			Map<String, Object> criteria) throws EntityException
	{
		List<Predicate> predicateList = super.setCriteria(criteriaBuilder, criteriaRoot, criteria);
		Number purchaseId = (Number) criteria.get(EntityConstants.Purchase.purchaseId);
		if (purchaseId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Purchase.purchaseId), purchaseId.longValue()));
		Number lastPurchaseId = (Number) criteria.get(EntityConstants.Purchase.lastPurchaseId);
		if (lastPurchaseId != null)
			predicateList.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Purchase.purchaseId), lastPurchaseId.longValue()));

		Number agentId = (Number) criteria.get(EntityConstants.Purchase.agentId);
		if (agentId != null)
		{
			Join<Purchase, Agent> purchaseAgentJoin = criteriaRoot.join(EntityConstants.Purchase.agent);
			predicateList.add(criteriaBuilder.equal(purchaseAgentJoin.get(EntityConstants.Agent.agentId), agentId));
		}
		Number profileId = (Number) criteria.get(EntityConstants.Purchase.profileId);
		String profileMobile = (String) criteria.get(EntityConstants.Purchase.profile_mobile);
		if (profileMobile != null || profileId != null)
		{
			Join<Purchase, Profile> purchaseProfileJoin = criteriaRoot.join(EntityConstants.Purchase.profile);
			if (profileMobile != null && profileMobile.trim().length() > 0)
				predicateList.add(criteriaBuilder.equal(purchaseProfileJoin.get(EntityConstants.Purchase.profile_mobile), profileMobile));
			if (profileId != null)
				predicateList.add(criteriaBuilder.equal(purchaseProfileJoin.get(EntityConstants.Profile.profileId), profileId));
		}
		// Long insertDate_From = (Long) criteria.get(EntityConstants.Purchase.insertDate_From_Search);
		// if (insertDate_From != null)
		// predicateList.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Purchase.insertDate), getDateFromLong(insertDate_From)));
		// Long insertDate_To = (Long) criteria.get(EntityConstants.Purchase.insertDate_To_Search);
		// if (insertDate_To != null)
		// predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.Purchase.insertDate), getDateFromLong(insertDate_To)));

		String fundOnly = (String) criteria.get(EntityConstants.Purchase.fundOnly_Search);
		if (fundOnly != null)
			predicateList.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Purchase.txnType), (byte) 0));
		String refundOnly = (String) criteria.get(EntityConstants.Purchase.refundOnly_Search);
		if (refundOnly != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.Purchase.txnType), (byte) 0));

		Number branchId = (Number) criteria.get(EntityConstants.Purchase.branchId);
		if (branchId != null)
		{
			Join<Purchase, Branch> purchaseBranchJoin = criteriaRoot.join(EntityConstants.Purchase.branch);
			predicateList.add(criteriaBuilder.equal(purchaseBranchJoin.get(EntityConstants.Branch.branchId), branchId));
		}
		Number invoiceId = (Number) criteria.get(EntityConstants.Purchase.invoiceId);
		if (invoiceId != null)
		{
			Join<Purchase, SysInvoice> purchaseInvoiceJoin = criteriaRoot.join(EntityConstants.Purchase.sysInvoice);
			predicateList.add(criteriaBuilder.equal(purchaseInvoiceJoin.get(EntityConstants.SysInvoice.sysInvoiceId), invoiceId));
		}
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{

		long id = ((Number) data.get(EntityConstants.Purchase.purchaseId)).longValue();
		setPointsId(id);
		setPurchaseId(id);
	}

	@Override
	public Object getEntityId()
	{
		long id = super.getPointsId();
		return id > 0 ? id : purchaseId;
	}

	@Override
	public <T extends AbsoluteEntity> Order[] getDefaultOrderFields(CriteriaBuilder criteriaBuilder, Root<T> criteriaRoot)
	{
		return new Order[]
			{ criteriaBuilder.desc(criteriaRoot.get(EntityConstants.Purchase.insertDate)) };
	}
}
