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
 * The persistent class for the currency database table.
 */
@Entity
@Table(name = "currency")
@NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c")
public class Currency extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Currency.currencyId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_id", unique = true, nullable = false)
	private short currencyId;

	@Column(name = "currency_name", nullable = false, length = 10)
	private String currencyName;

	@Column(name = "currency_sign", nullable = false, length = 5)
	private String currencySign;

	@Column(name = "points_value", nullable = false)
	private float pointsValue;

	@Column(name = "value_egp", nullable = false)
	private float valueEgp;

	// bi-directional many-to-one association to Contract
	@OneToMany(mappedBy = "currency")
	private List<Contract> contracts;

	// bi-directional many-to-one association to Country
	@OneToMany(mappedBy = "currency")
	private List<Country> countries;

	// bi-directional many-to-one association to PointsExchange
	@OneToMany(mappedBy = "currency")
	private List<PointsExchange> pointsExchanges;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "currency")
	private List<Product> products;

	// bi-directional many-to-one association to Promotion
	@OneToMany(mappedBy = "currency")
	private List<Promotion> promotions;

	// bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy = "currency")
	private List<Purchase> purchases;

	// bi-directional many-to-one association to SysInvoice
	@OneToMany(mappedBy = "currency")
	private List<SysInvoice> sysInvoices;

	// bi-directional many-to-one association to SysPayment
	@OneToMany(mappedBy = "currency")
	private List<SysPayment> sysPayments;

	public Currency()
	{
	}

	public short getCurrencyId()
	{
		return this.currencyId;
	}

	public void setCurrencyId(short currencyId)
	{
		this.currencyId = currencyId;
	}

	public String getCurrencyName()
	{
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName)
	{
		this.currencyName = currencyName;
	}

	public String getCurrencySign()
	{
		return this.currencySign;
	}

	public void setCurrencySign(String currencySign)
	{
		this.currencySign = currencySign;
	}

	public float getPointsValue()
	{
		return this.pointsValue;
	}

	public void setPointsValue(float pointsValue)
	{
		this.pointsValue = pointsValue;
	}

	public float getValueEgp()
	{
		return this.valueEgp;
	}

	public void setValueEgp(float valueEgp)
	{
		this.valueEgp = valueEgp;
	}

	public List<Contract> getContracts()
	{
		return this.contracts;
	}

	public void setContracts(List<Contract> contracts)
	{
		this.contracts = contracts;
	}

	public Contract addContract(Contract contract)
	{
		getContracts().add(contract);
		contract.setCurrency(this);

		return contract;
	}

	public Contract removeContract(Contract contract)
	{
		getContracts().remove(contract);
		contract.setCurrency(null);

		return contract;
	}

	public List<Country> getCountries()
	{
		return this.countries;
	}

	public void setCountries(List<Country> countries)
	{
		this.countries = countries;
	}

	public Country addCountry(Country country)
	{
		getCountries().add(country);
		country.setCurrency(this);

		return country;
	}

	public Country removeCountry(Country country)
	{
		getCountries().remove(country);
		country.setCurrency(null);

		return country;
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
		pointsExchange.setCurrency(this);

		return pointsExchange;
	}

	public PointsExchange removePointsExchange(PointsExchange pointsExchange)
	{
		getPointsExchanges().remove(pointsExchange);
		pointsExchange.setCurrency(null);

		return pointsExchange;
	}

	public List<Product> getProducts()
	{
		return this.products;
	}

	public void setProducts(List<Product> products)
	{
		this.products = products;
	}

	public Product addProduct(Product product)
	{
		getProducts().add(product);
		product.setCurrency(this);

		return product;
	}

	public Product removeProduct(Product product)
	{
		getProducts().remove(product);
		product.setCurrency(null);

		return product;
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
		promotion.setCurrency(this);

		return promotion;
	}

	public Promotion removePromotion(Promotion promotion)
	{
		getPromotions().remove(promotion);
		promotion.setCurrency(null);

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

	public Purchase addPurchas(Purchase purchas)
	{
		getPurchases().add(purchas);
		purchas.setCurrency(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas)
	{
		getPurchases().remove(purchas);
		purchas.setCurrency(null);

		return purchas;
	}

	public List<SysInvoice> getSysInvoices()
	{
		return this.sysInvoices;
	}

	public void setSysInvoices(List<SysInvoice> sysInvoices)
	{
		this.sysInvoices = sysInvoices;
	}

	public SysInvoice addSysInvoice(SysInvoice sysInvoice)
	{
		getSysInvoices().add(sysInvoice);
		sysInvoice.setCurrency(this);

		return sysInvoice;
	}

	public SysInvoice removeSysInvoice(SysInvoice sysInvoice)
	{
		getSysInvoices().remove(sysInvoice);
		sysInvoice.setCurrency(null);

		return sysInvoice;
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
		sysPayment.setCurrency(this);

		return sysPayment;
	}

	public SysPayment removeSysPayment(SysPayment sysPayment)
	{
		getSysPayments().remove(sysPayment);
		sysPayment.setCurrency(null);

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
		map.put(EntityConstants.Currency.currencyId, currencyId);
		map.put(EntityConstants.Currency.currencyName, currencyName);
		map.put(EntityConstants.Currency.valueEgp, valueEgp);
		map.put(EntityConstants.Currency.pointsValue, pointsValue);
		map.put(EntityConstants.Currency.currencySign, currencySign);
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setCurrencyName((String) data.get(EntityConstants.Currency.currencyName));
		setValueEgp(((Number) data.get(EntityConstants.Currency.valueEgp)).floatValue());
		setPointsValue(((Number) data.get(EntityConstants.Currency.pointsValue)).floatValue());
		setCurrencySign((String) data.get(EntityConstants.Currency.currencySign));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Object currencyIdSe = criteria.get(EntityConstants.Currency.currencyId);
		if (currencyIdSe != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Currency.currencyId), currencyIdSe));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setCurrencyId(((Number) data.get(EntityConstants.Currency.currencyId)).shortValue());
	}

	@Override
	public Object getEntityId()
	{
		return getCurrencyId();
	}
}
