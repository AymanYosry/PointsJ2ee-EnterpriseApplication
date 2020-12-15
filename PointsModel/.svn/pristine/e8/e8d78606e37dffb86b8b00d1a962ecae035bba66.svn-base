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
 * The persistent class for the role database table.
 */
@Entity
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role extends AbsoluteEntity implements Serializable
{
	//
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Role.roleId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	private int roleId;

	@Column(name = "role_name", nullable = false, length = 45)
	private String roleName;

	// bi-directional many-to-one association to Profile
	@OneToMany(mappedBy = "role")
	private List<Profile> profiles;

	public Role()
	{

	}

	public int getRoleId()
	{
		return this.roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return this.roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
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
		profile.setRole(this);

		return profile;
	}

	public Profile removeProfile(Profile profile)
	{
		getProfiles().remove(profile);
		profile.setRole(null);

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
		map.put(EntityConstants.Role.roleId, roleId);
		map.put(EntityConstants.Role.roleName, roleName);
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setRoleName((String) data.get(EntityConstants.Role.roleName));
	}

	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
	{

		List<Predicate> predicateList = new ArrayList<Predicate>();
		Object roleIdSe = criteria.get(EntityConstants.Role.roleId);
		if (roleIdSe != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Role.roleId), roleIdSe));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setRoleId(((Number) data.get(EntityConstants.Role.roleId)).intValue());
	}

	@Override
	public Object getEntityId()
	{
		return getRoleId();
	}
}
