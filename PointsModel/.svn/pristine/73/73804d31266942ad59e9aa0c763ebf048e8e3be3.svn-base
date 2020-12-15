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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the branch database table.
 */
@Entity
@Table(name = "branch")

@NamedQueries(value =
{ @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b") })
public class Branch extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Branch.branchId };

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id", unique = true, nullable = false)
	private long branchId;

	@Column(nullable = false, length = 255)
	private String address;

	@Column(name = "branch_name", nullable = false, length = 100)
	private String branchName;

	@Column(name = "location_latitude", nullable = false)
	private double locationLatitude;

	@Column(name = "location_longitude", nullable = false)
	private double locationLongitude;

	@Column(nullable = false, length = 20)
	private String tel;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "`branch-agent_id`", nullable = false)
	private Agent agent;

	// bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name = "branch_state_id", referencedColumnName = "state_id", nullable = false)
	private State state;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "branch_country_id", referencedColumnName = "country_id", nullable = false)
	private Country country;

	// bi-directional many-to-one association to PointsExchange
	@OneToMany(mappedBy = "branch")
	private List<PointsExchange> pointsExchanges;

	// bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy = "branch")
	private List<Purchase> purchases;

	public Branch()
	{
	}

	public long getBranchId()
	{
		return this.branchId;
	}

	public void setBranchId(long branchId)
	{
		this.branchId = branchId;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getBranchName()
	{
		return this.branchName;
	}

	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	public double getLocationLatitude()
	{
		return this.locationLatitude;
	}

	public void setLocationLatitude(double locationLatitude)
	{
		this.locationLatitude = locationLatitude;
	}

	public double getLocationLongitude()
	{
		return this.locationLongitude;
	}

	public void setLocationLongitude(double locationLongitude)
	{
		this.locationLongitude = locationLongitude;
	}

	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public Agent getAgent()
	{
		return this.agent;
	}

	public void setAgent(Agent agent)
	{
		this.agent = agent;
	}

	public State getState()
	{
		return this.state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public Country getCountry()
	{
		return this.country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
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
		pointsExchange.setBranch(this);

		return pointsExchange;
	}

	public PointsExchange removePointsExchange(PointsExchange pointsExchange)
	{
		getPointsExchanges().remove(pointsExchange);
		pointsExchange.setBranch(null);

		return pointsExchange;
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
		purchas.setBranch(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas)
	{
		getPurchases().remove(purchas);
		purchas.setBranch(null);

		return purchas;
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
		map.put(EntityConstants.Branch.branchId, branchId);
		map.put(EntityConstants.Branch.branchName, branchName);
		map.put(EntityConstants.Branch.tel, tel);
		map.put(EntityConstants.Branch.address, address);
		map.put(EntityConstants.Branch.locationLongitude, locationLongitude);
		map.put(EntityConstants.Branch.locationLatitude, locationLatitude);
		map.put(EntityConstants.Branch.agentId, agent.getAgentId());
		map.put(EntityConstants.Branch.agent, agent.transformMainDataToMap());
		map.put(EntityConstants.Branch.stateId, state.getStateId());
		map.put(EntityConstants.Branch.state, state.transformMainDataToMap());
		map.put(EntityConstants.Branch.countryId, country.getCountryId());
		map.put(EntityConstants.Branch.country, country.transformMainDataToMap());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setBranchName((String) data.get(EntityConstants.Branch.branchName));
		setTel((String) data.get(EntityConstants.Branch.tel));
		setAddress((String) data.get(EntityConstants.Branch.address));
		setLocationLongitude(((Number) data.get(EntityConstants.Branch.locationLongitude)).doubleValue());
		setLocationLatitude(((Number) data.get(EntityConstants.Branch.locationLatitude)).doubleValue());
		// Number agentIdNumber = ((Number) data.get(EntityConstants.Branch.agentId));
		// if (agentIdNumber != null)
		// {
		// Agent agent = new Agent();
		// agent.setAgentId(agentIdNumber.longValue());
		// setAgent(agent);
		// }
		// StatePK statePK = new StatePK();
		// statePK.setCountryId(((Number) data.get(EntityConstants.Branch.stateId)).byteValue());
		// statePK.setStateId(((Number) data.get(EntityConstants.Branch.countryId)).byteValue());
		// State state = new State();
		// state.setId(statePK);
		// setState(state);
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Number branchId = (Number) criteria.get(EntityConstants.Branch.branchId);
		if (branchId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Branch.branchId), branchId.longValue()));
		Number agentId = (Number) criteria.get(EntityConstants.Branch.agentId);
		if (agentId != null)
		{
			Join<Branch, Agent> branchAgentJoin = criteriaRoot.join(EntityConstants.Branch.agent);
			predicateList.add(criteriaBuilder.equal(branchAgentJoin.get(EntityConstants.Agent.agentId), agentId.longValue()));
		}
		Number countryIdSearch = (Number) criteria.get(EntityConstants.Branch.countryId);
		if (countryIdSearch != null)
		{
			Join<Branch, Country> branchCountryJoin = criteriaRoot.join(EntityConstants.Branch.country);
			predicateList.add(criteriaBuilder.equal(branchCountryJoin.get(EntityConstants.Country.countryId), countryIdSearch.byteValue()));
		}
		Number stateId = (Number) criteria.get(EntityConstants.Branch.stateId);
		if (stateId != null)
		{
			Join<Branch, State> branchStateJoin = criteriaRoot.join(EntityConstants.Branch.state);
			predicateList.add(criteriaBuilder.equal(branchStateJoin.get(EntityConstants.State.stateId), stateId.intValue()));
		}
		Number locationLatitude_From = (Number) criteria.get(EntityConstants.Branch.locationLatitude_From_Search);
		if (locationLatitude_From != null)
			predicateList
					.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Branch.locationLatitude), locationLatitude_From.doubleValue()));

		Number locationLatitude_TO = (Number) criteria.get(EntityConstants.Branch.locationLatitude_To_Search);
		if (locationLatitude_TO != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.Branch.locationLatitude), locationLatitude_TO.doubleValue()));

		Number locationLongitude_From = (Number) criteria.get(EntityConstants.Branch.locationLongitude_From_Search);
		if (locationLongitude_From != null)
			predicateList.add(
					criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Branch.locationLongitude), locationLongitude_From.doubleValue()));

		Number locationLongitude_To = (Number) criteria.get(EntityConstants.Branch.locationLatitude_To_Search);
		if (locationLongitude_To != null)
			predicateList
					.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.Branch.locationLongitude), locationLongitude_To.doubleValue()));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setBranchId(((Number) data.get(EntityConstants.Branch.branchId)).longValue());
	}
}
