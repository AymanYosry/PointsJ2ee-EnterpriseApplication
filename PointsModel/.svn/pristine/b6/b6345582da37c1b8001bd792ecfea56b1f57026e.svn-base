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
 * The persistent class for the states database table.
 */
@Entity
@Table(name = "states")
@NamedQuery(name = "State.findAll", query = "SELECT s FROM State s")
public class State extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ /* EntityConstants.State.countryId, */ EntityConstants.State.stateId };

	// @EmbeddedId
	// private StatePK id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id", unique = true, nullable = false)
	private int stateId;

	@Column(name = "state_name", nullable = false, length = 45)
	private String stateName;

	// bi-directional many-to-one association to Branch
	@OneToMany(mappedBy = "state")
	private List<Branch> branches;

	// bi-directional many-to-one association to District
	@OneToMany(mappedBy = "state")
	private List<District> districts;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "state_country_id", nullable = false)
	private Country country;

	public State()
	{
	}

	public int getStateId()
	{
		return stateId;
	}

	public void setStateId(int stateId)
	{
		this.stateId = stateId;
	}

	// public StatePK getId()
	// {
	// return this.id;
	// }
	//
	// public void setId(StatePK id)
	// {
	// this.id = id;
	// }

	public String getStateName()
	{
		return this.stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public List<Branch> getBranches()
	{
		return this.branches;
	}

	public void setBranches(List<Branch> branches)
	{
		this.branches = branches;
	}

	public Branch addBranch(Branch branch)
	{
		getBranches().add(branch);
		branch.setState(this);

		return branch;
	}

	public Branch removeBranch(Branch branch)
	{
		getBranches().remove(branch);
		branch.setState(null);

		return branch;
	}

	public List<District> getDistricts()
	{
		return this.districts;
	}

	public void setDistricts(List<District> districts)
	{
		this.districts = districts;
	}

	public District addDistrict(District district)
	{
		getDistricts().add(district);
		district.setState(this);

		return district;
	}

	public District removeDistrict(District district)
	{
		getDistricts().remove(district);
		district.setState(null);

		return district;
	}

	public Country getCountry()
	{
		return this.country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
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
		map.put(EntityConstants.State.stateId, getStateId());
		map.put(EntityConstants.State.stateName, stateName);
		map.put(EntityConstants.State.countryId, country.getCountryId());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setStateName((String) data.get(EntityConstants.State.stateName));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Number stateId = (Number) criteria.get(EntityConstants.State.stateId);
		if (stateId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.State.stateId), stateId.intValue()));

		Number countryIdSearch = (Number) criteria.get(EntityConstants.State.countryId);
		if (countryIdSearch != null)
		{
			Join<State, Country> stateCountryJoin = criteriaRoot.join(EntityConstants.State.country);
			predicateList.add(criteriaBuilder.equal(stateCountryJoin.get(EntityConstants.Country.countryId), countryIdSearch.byteValue()));
		}
		String stateNameSearch = (String) criteria.get(EntityConstants.State.stateName);
		if (stateNameSearch != null && !stateNameSearch.trim().isEmpty())
			predicateList.add(criteriaBuilder.like(criteriaRoot.get(EntityConstants.State.stateName), stateNameSearch + "%"));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		// StatePK statePK = new StatePK();
		// statePK.setStateId(((Number) data.get(EntityConstants.State.stateId)).intValue());
		// statePK.setCountryId(((Number) data.get(EntityConstants.State.countryId)).byteValue());
		setStateId(((Number) data.get(EntityConstants.State.stateId)).intValue());
	}

	@Override
	public Object getEntityId()
	{
		return getStateId();
	}
}
