package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
import com.ewhale.points.model.entity.interfaces.UpdateTracking;

/**
 * The persistent class for the promotion database table.
 */
@Entity
@Table(name = "promotion")
@NamedQueries(value =
	{ @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"),
			@NamedQuery(name = "Promotion.lastUpdateDateNamedQuery",
					query = "SELECT max(p.insertDate) ,max(p.lastUpdDate) ,max(p.statusDate)   FROM Promotion p") })

@PrimaryKeyJoinColumn(name = "promotion_id", referencedColumnName = "item_status_id")
public class Promotion extends ItemStatus implements Serializable, UpdateTracking, InsertTracking
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Promotion.promotionId };
	public static final String lastUpdateDateNamedQuery = "Promotion.lastUpdateDateNamedQuery";

	@Column(name = "promotion_id", unique = true, nullable = false, insertable = false, updatable = false)
	private long promotionId;

	@Lob
	private byte[] image;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_date", nullable = false)
	private Date insertDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_upd_date")
	private Date lastUpdDate;

	@Column(nullable = false, length = 100)
	private String message;

	@Column(name = "message_details", nullable = false, length = 255)
	private String messageDetails;

	@Temporal(TemporalType.DATE)
	@Column(name = "promotion_date", nullable = false)
	private Date promotionDate;

	@Column(name = "promotion_fees", nullable = false)
	private float promotionFees;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "promotion_country_id", referencedColumnName = "country_id", nullable = false)
	private Country country;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "promotion_agent_id", nullable = false)
	private Agent agent;

	// bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name = "promotion_currency_id", nullable = false)
	private Currency currency;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "ins_emp_id", nullable = false)
	private Profile insEmp;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "upd_emp_id")
	private Profile updateEmp;

	// bi-directional many-to-one association to SysInvoice
	@ManyToOne
	@JoinColumn(name = "promotion_sys_invoice_id")
	private SysInvoice sysInvoice;

	public Promotion()
	{
	}

	public long getPromotionId()
	{
		return this.promotionId;
	}

	public void setPromotionId(long promotionId)
	{
		this.promotionId = promotionId;
		setItemId(promotionId);
	}

	public byte[] getImage()
	{
		return this.image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}

	public Date getInsertDate()
	{
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public Date getLastUpdDate()
	{
		return this.lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate)
	{
		this.lastUpdDate = lastUpdDate;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessageDetails()
	{
		return this.messageDetails;
	}

	public void setMessageDetails(String messageDetails)
	{
		this.messageDetails = messageDetails;
	}

	public Date getPromotionDate()
	{
		return this.promotionDate;
	}

	public void setPromotionDate(Date promotionDate)
	{
		this.promotionDate = promotionDate;
	}

	public float getPromotionFees()
	{
		return this.promotionFees;
	}

	public void setPromotionFees(float promotionFees)
	{
		this.promotionFees = promotionFees;
	}

	public Country getCountry()
	{
		return country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
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

	public Profile getInsEmp()
	{
		return this.insEmp;
	}

	public void setInsEmp(Profile insEmp)
	{
		this.insEmp = insEmp;
	}

	public Profile getUpdateEmp()
	{
		return this.updateEmp;
	}

	public void setUpdateEmp(Profile updateEmp)
	{
		this.updateEmp = updateEmp;
	}

	public SysInvoice getSysInvoice()
	{
		return this.sysInvoice;
	}

	public void setSysInvoice(SysInvoice sysInvoice)
	{
		this.sysInvoice = sysInvoice;
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
		map.put(EntityConstants.Promotion.promotionId, promotionId);
		map.put(EntityConstants.Promotion.message, message);
		map.put(EntityConstants.Promotion.messageDetails, messageDetails);
		map.put(EntityConstants.Promotion.image, image);
		map.put(EntityConstants.Promotion.promotionFees, promotionFees);
		map.put(EntityConstants.Promotion.promotionDate, getStringFromDate(promotionDate));
		map.put(EntityConstants.Promotion.insertDate, getStringFromDate(insertDate));
		map.put(EntityConstants.Promotion.lastUpdDate, getStringFromDate(lastUpdDate));
		map.put(EntityConstants.Promotion.insEmpFullName, insEmp.getFullName());
		if (updateEmp != null)
			map.put(EntityConstants.Promotion.updateEmpFullName, updateEmp.getFullName());
		map.put(EntityConstants.Promotion.currencyId, currency.getCurrencyId());
		map.put(EntityConstants.Promotion.currency, currency.transformMainDataToMap());
		map.put(EntityConstants.Promotion.countryId, country.getCountryId());
		map.put(EntityConstants.Promotion.country, country.transformMainDataToMap());
		map.put(EntityConstants.Promotion.agentId, agent.getAgentId());
		map.put(EntityConstants.Promotion.agent, agent.transformMainDataToMap());
		map.put(EntityConstants.Promotion.invoiceId, sysInvoice.getSysInvoiceId());
		map.put(EntityConstants.Promotion.sysInvoice, sysInvoice.transformMainDataToMap());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setMessage((String) data.get(EntityConstants.Promotion.message));
		setMessageDetails((String) data.get(EntityConstants.Promotion.messageDetails));
		setImage(Base64.getDecoder().decode((String) data.get(EntityConstants.Promotion.image)));
		// this will be calculated in the facade
		// setPromotionFees(((Number) data.get(EntityConstants.Promotion.promotionFees)).floatValue());
		setPromotionDate(getDateFromLong((Long) data.get(EntityConstants.Promotion.promotionDate)));

	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot,
			Map<String, Object> criteria) throws EntityException
	{
		List<Predicate> predicateList = super.setCriteria(criteriaBuilder, criteriaRoot, criteria);
		Number promotionId = (Number) criteria.get(EntityConstants.Promotion.promotionId);
		if (promotionId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Promotion.promotionId), promotionId.longValue()));

		Long promotionDate_From = (Long) criteria.get(EntityConstants.Promotion.promotionDate_From_Search);
		if (promotionDate_From != null)
			predicateList
					.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Promotion.promotionDate), getDateFromLong(promotionDate_From)));
		Long promotionDate_To = (Long) criteria.get(EntityConstants.Promotion.promotionDate_To_Search);
		if (promotionDate_To != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.Promotion.promotionDate), getDateFromLong(promotionDate_To)));
		Number countryIdSearch = (Number) criteria.get(EntityConstants.Promotion.countryId);
		if (countryIdSearch != null)
		{
			Join<Promotion, Country> promotionCountryJoin = criteriaRoot.join(EntityConstants.Promotion.country);
			predicateList.add(criteriaBuilder.equal(promotionCountryJoin.get(EntityConstants.Country.countryId), countryIdSearch.byteValue()));
		}
		Number invoiceId = (Number) criteria.get(EntityConstants.Promotion.invoiceId);
		if (invoiceId != null)
		{
			Join<Promotion, SysInvoice> promotionInvoiceJoin = criteriaRoot.join(EntityConstants.Promotion.sysInvoice);
			predicateList.add(criteriaBuilder.equal(promotionInvoiceJoin.get(EntityConstants.SysInvoice.sysInvoiceId), invoiceId));
		}
		Number agentId = (Number) criteria.get(EntityConstants.Promotion.agentId);
		if (agentId != null)
		{
			Join<Promotion, Agent> promotionAgentJoin = criteriaRoot.join(EntityConstants.Promotion.agent);
			predicateList.add(criteriaBuilder.equal(promotionAgentJoin.get(EntityConstants.Agent.agentId), agentId));
		}
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		long id = ((Number) data.get(EntityConstants.Promotion.promotionId)).longValue();
		setItemId(id);
		setPromotionId(id);
	}

	@Override
	public Object getEntityId()
	{
		return getPromotionId();
	}

	@Override
	public void setEntityId(Object idObj)
	{
		long id = ((Number) idObj).longValue();
		setItemId(id);
		setPromotionId(id);
	}
}
