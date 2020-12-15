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
 * The persistent class for the district database table.
 */
@Entity
@Table(name = "district")
@NamedQuery(name = "District.findAll", query = "SELECT d FROM District d")
public class District extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.District.districtId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "district_id", unique = true, nullable = false)
	private int districtId;

	@Column(name = "district_name", nullable = false, length = 45)
	private String districtName;

	// bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name = "district_state_id", referencedColumnName = "state_id", nullable = false)
	private State state;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "district_country_id", referencedColumnName = "country_id", nullable = false)
	private Country country;

	// bi-directional many-to-one association to Profile
	@OneToMany(mappedBy = "district")
	private List<Profile> profiles;

	public District()
	{
	}

	// public DistrictPK getId()
	// {
	// return this.id;
	// }

	// public void setId(DistrictPK id)
	// {
	// this.id = id;
	// }

	public int getDistrictId()
	{
		return districtId;
	}

	public void setDistrictId(int districtId)
	{
		this.districtId = districtId;
	}

	public String getDistrictName()
	{
		return this.districtName;
	}

	public void setDistrictName(String districtName)
	{
		this.districtName = districtName;
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
		return country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}

	public List<Profile> getProfiles()
	{
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles)
	{
		this.profiles = profiles;
	}

	public Profile addProfile(Profile profile)
	{
		getProfiles().add(profile);
		profile.setDistrict(this);

		return profile;
	}

	public Profile removeProfile(Profile profile)
	{
		getProfiles().remove(profile);
		profile.setDistrict(null);

		return profile;
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
		map.put(EntityConstants.District.districtId, getDistrictId());
		map.put(EntityConstants.District.districtName, districtName);
		map.put(EntityConstants.District.stateId, state.getStateId());
		map.put(EntityConstants.District.countryId, country.getCountryId());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setDistrictName((String) data.get(EntityConstants.District.districtName));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();

		Number districtId = (Number) criteria.get(EntityConstants.District.districtId);
		if (districtId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.District.districtId), districtId.intValue()));

		Number stateId = (Number) criteria.get(EntityConstants.District.stateId);
		if (stateId != null)
		{
			Join<District, State> districtStateJoin = criteriaRoot.join(EntityConstants.District.state);
			predicateList.add(criteriaBuilder.equal(districtStateJoin.get(EntityConstants.State.stateId), stateId.intValue()));
		}
		Number countryIdSearch = (Number) criteria.get(EntityConstants.District.countryId);
		if (countryIdSearch != null)
		{
			Join<Branch, Country> districtCountryJoin = criteriaRoot.join(EntityConstants.District.country);
			predicateList.add(criteriaBuilder.equal(districtCountryJoin.get(EntityConstants.Country.countryId), countryIdSearch.byteValue()));
		}

		String districtName = (String) criteria.get(EntityConstants.District.districtName);
		if (districtName != null)
			predicateList.add(criteriaBuilder.like(criteriaRoot.get(EntityConstants.District.districtName), districtName + "%"));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		// DistrictPK districtPK = new DistrictPK();
		// districtPK.setDistrictId(((Number) data.get(EntityConstants.District.districtId)).intValue());
		// districtPK.setStateId(((Number) data.get(EntityConstants.District.stateId)).intValue());
		// districtPK.setCountryId(((Number) data.get(EntityConstants.District.countryId)).byteValue());
		setDistrictId(((Number) data.get(EntityConstants.District.districtId)).intValue());
	}

	@Override
	public Object getEntityId()
	{
		return getDistrictId();
	}
}
