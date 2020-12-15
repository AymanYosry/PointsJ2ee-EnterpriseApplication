package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the country database table.
 */
@Entity
@Table(name = "country")
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
public class Country extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Country.countryId };

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id", unique = true, nullable = false)
	private byte countryId;

	@Column(name = "country_name", nullable = false, length = 45)
	private String countryName;

	// bi-directional many-to-one association to Contract
	@OneToMany(mappedBy = "country")
	private List<Contract> contracts;

	// bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name = "country_currency_id", nullable = false)
	private Currency currency;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "country")
	private List<Product> products;

	// bi-directional many-to-one association to State
	@OneToMany(mappedBy = "country")
	private List<State> states;

	public Country()
	{
	}

	public byte getCountryId()
	{
		return this.countryId;
	}

	public void setCountryId(byte countryId)
	{
		this.countryId = countryId;
	}

	public String getCountryName()
	{
		return this.countryName;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
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
		contract.setCountry(this);

		return contract;
	}

	public Contract removeContract(Contract contract)
	{
		getContracts().remove(contract);
		contract.setCountry(null);

		return contract;
	}

	public Currency getCurrency()
	{
		return this.currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
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
		product.setCountry(this);

		return product;
	}

	public Product removeProduct(Product product)
	{
		getProducts().remove(product);
		product.setCountry(null);

		return product;
	}

	public List<State> getStates()
	{
		return this.states;
	}

	public void setStates(List<State> states)
	{
		this.states = states;
	}

	public State addState(State state)
	{
		getStates().add(state);
		state.setCountry(this);

		return state;
	}

	public State removeState(State state)
	{
		getStates().remove(state);
		state.setCountry(null);

		return state;
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
		map.put(EntityConstants.Country.countryId, countryId);
		map.put(EntityConstants.Country.countryName, countryName);
		map.put(EntityConstants.Country.currency, currency.transformToMap());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		setEntityId(data);
		setCountryName((String) data.get(EntityConstants.Country.countryName));
		Currency currency = new Currency();
		currency.setCurrencyId(((Number) data.get(EntityConstants.Country.currencyId)).shortValue());
		setCurrency(currency);
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Object countryIdSe = criteria.get(EntityConstants.Country.countryId);
		if (countryIdSe != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Country.countryId), countryIdSe));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setCountryId(((Number) data.get(EntityConstants.Country.countryId)).byteValue());
	}

	@Override
	public Object getEntityId()
	{
		return getCountryId();
	}

}
