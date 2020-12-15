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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.model.entity.interfaces.InsertTracking;
import com.ewhale.points.model.entity.interfaces.UpdateTracking;

/**
 * The persistent class for the product database table.
 */
@Entity
@Table(name = "product")

@NamedQueries(value =
{ @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
	@NamedQuery(name = "Product.lastUpdateDateNamedQuery",
	query = "SELECT max(p.insertDate) ,max(p.lastUpdDate) ,max(p.statusDate)   FROM Product p")})
@PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "item_status_id")
public class Product extends ItemStatus implements Serializable, UpdateTracking, InsertTracking
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Product.productId };

	public static final String lastUpdateDateNamedQuery = "Product.lastUpdateDateNamedQuery";

	@Column(name = "product_id", unique = true, nullable = false, insertable = false, updatable = false)
	private long productId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_date", nullable = false)
	private Date insertDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_upd_date")
	private Date lastUpdDate;

	@Lob
	@Column(nullable = false)
	private byte[] photo;

	@Column(nullable = false)
	private float price;

	@Column(name = "product_name", nullable = false, length = 150)
	private String productName;

	@Temporal(TemporalType.DATE)
	@Column(name = "validity_date", nullable = false)
	private Date validityDate;

	// bi-directional many-to-one association to PointsExchange
	@OneToMany(mappedBy = "product")
	private List<PointsExchange> pointsExchanges;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "ins_agent_id", nullable = false)
	private Agent agent;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "product_country_id", nullable = false)
	private Country country;

	// bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name = "product_currency_id", nullable = false)
	private Currency currency;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "ins_emp_id", nullable = false)
	private Profile insEmp;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "upd_emp_id")
	private Profile updateEmp;

	public Product()
	{
	}

	public long getProductId()
	{
		return this.productId;
	}

	public void setProductId(long productId)
	{
		this.productId = productId;
		setItemId(productId);
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

	public byte[] getPhoto()
	{
		return this.photo;
	}

	public void setPhoto(byte[] photo)
	{
		this.photo = photo;
	}

	public float getPrice()
	{
		return this.price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	private short getPointsValue()
	{
		return (short) (price * currency.getPointsValue());
	}

	public String getProductName()
	{
		return this.productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public Date getValidityDate()
	{
		return this.validityDate;
	}

	public void setValidityDate(Date validityDate)
	{
		this.validityDate = validityDate;
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
		pointsExchange.setProduct(this);

		return pointsExchange;
	}

	public PointsExchange removePointsExchange(PointsExchange pointsExchange)
	{
		getPointsExchanges().remove(pointsExchange);
		pointsExchange.setProduct(null);

		return pointsExchange;
	}

	public Agent getAgent()
	{
		return this.agent;
	}

	public void setAgent(Agent agent)
	{
		this.agent = agent;
	}

	public Country getCountry()
	{
		return this.country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
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
		map.put(EntityConstants.Product.productId, productId);
		map.put(EntityConstants.Product.productName, productName);
		map.put(EntityConstants.Product.price, price);
		map.put(EntityConstants.Product.pointsValue, getPointsValue());
		map.put(EntityConstants.Product.validityDate, getStringFromDate(validityDate));
		map.put(EntityConstants.Product.photo, photo);
		map.put(EntityConstants.Product.insEmpId, insEmp.getProfileId());
		map.put(EntityConstants.Product.insertDate, insertDate);
		map.put(EntityConstants.Product.updateEmpId, updateEmp != null ? updateEmp.getProfileId() : null);

		map.put(EntityConstants.Product.currencyId, currency.getCurrencyId());
		map.put(EntityConstants.Product.currency, currency.transformMainDataToMap());
		map.put(EntityConstants.Product.countryId, country.getCountryId());
		map.put(EntityConstants.Product.country, country.transformMainDataToMap());
		map.put(EntityConstants.Product.agentId, agent.getAgentId());
		map.put(EntityConstants.Product.agent, agent.transformMainDataToMap());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);
		setProductName((String) data.get(EntityConstants.Product.productName));
		setPrice(((Number) data.get(EntityConstants.Product.price)).floatValue());
		setValidityDate(getDateFromLong((Long) data.get(EntityConstants.Product.validityDate)));
		setPhoto(Base64.getDecoder().decode((String) data.get(EntityConstants.Product.photo)));

	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = super.setCriteria(criteriaBuilder, criteriaRoot, criteria);
		Number productId = (Number) criteria.get(EntityConstants.Product.productId);
		if (productId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Product.productId), productId.longValue()));
		Number pointsValueMax = (Number) criteria.get(EntityConstants.Product.pointsValue_Max);
		if (pointsValueMax != null)
		{
			Join<Product, Currency> productCurrencyJoin = criteriaRoot.join(EntityConstants.Product.currency);
			Expression<Number> pointsvalue = criteriaBuilder.prod(criteriaRoot.get(EntityConstants.Product.price),
					productCurrencyJoin.get(EntityConstants.Currency.pointsValue));
			predicateList.add(criteriaBuilder.le(pointsvalue, pointsValueMax.intValue()));
		}
		Number pointsValueMin = (Number) criteria.get(EntityConstants.Product.pointsValue_Min);
		if (pointsValueMin != null)
			predicateList.add(criteriaBuilder.ge(criteriaRoot.get(EntityConstants.Product.pointsValue), pointsValueMin.intValue()));
		String productNameSearch = (String) criteria.get(EntityConstants.Product.productName);
		if (productNameSearch != null && !productNameSearch.trim().isEmpty())
			predicateList.add(criteriaBuilder.like(criteriaRoot.get(EntityConstants.Product.productName), productNameSearch + "%"));

		Number agentId = (Number) criteria.get(EntityConstants.Product.agentId);
		if (agentId != null)
		{
			Join<Product, Agent> productAgentJoin = criteriaRoot.join(EntityConstants.Product.agent);
			predicateList.add(criteriaBuilder.equal(productAgentJoin.get(EntityConstants.Agent.agentId), agentId.longValue()));
		}
		Number countryIdSearch = (Number) criteria.get(EntityConstants.Product.countryId);
		if (countryIdSearch != null)
		{
			Join<Product, Country> productCountryJoin = criteriaRoot.join(EntityConstants.Product.country);
			predicateList.add(criteriaBuilder.equal(productCountryJoin.get(EntityConstants.Country.countryId), countryIdSearch.byteValue()));
		}

		Long validityDate_From = (Long) criteria.get(EntityConstants.Product.validityDate_From_Search);
		if (validityDate_From != null)
			predicateList.add(
					criteriaBuilder.greaterThanOrEqualTo(criteriaRoot.get(EntityConstants.Product.validityDate), getDateFromLong(validityDate_From)));
		Long validityDate_To = (Long) criteria.get(EntityConstants.Product.validityDate_To_Search);
		if (validityDate_To != null)
			predicateList
					.add(criteriaBuilder.lessThanOrEqualTo(criteriaRoot.get(EntityConstants.Product.validityDate), getDateFromLong(validityDate_To)));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		long id = ((Number) data.get(EntityConstants.Product.productId)).longValue();
		setItemId(id);
		setProductId(id);
	}

	@Override
	public Object getEntityId()
	{
		return getProductId();
	}

	@Override
	public void setEntityId(Object idObj)
	{
		long id = ((Number) idObj).longValue();
		setItemId(id);
		setProductId(id);
	}
}
