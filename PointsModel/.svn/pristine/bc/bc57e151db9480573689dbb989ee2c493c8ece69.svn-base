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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the category database table.
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category extends AbsoluteEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Category.categoryId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	private short categoryId;

	@Column(name = "category_name", nullable = false, length = 45)
	private String categoryName;

	// bi-directional many-to-many association to Agent
	@ManyToMany(mappedBy = "categories")
	private List<Agent> agents;

	public Category()
	{
	}

	public short getCategoryId()
	{
		return this.categoryId;
	}

	public void setCategoryId(short categoryId)
	{
		this.categoryId = categoryId;
	}

	public String getCategoryName()
	{
		return this.categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public List<Agent> getAgents()
	{
		return this.agents;
	}

	public void setAgents(List<Agent> agents)
	{
		this.agents = agents;
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
		map.put(EntityConstants.Category.categoryId, categoryId);
		map.put(EntityConstants.Category.categoryName, categoryName);

		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setCategoryName((String) data.get(EntityConstants.Category.categoryName));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Object categoryIdSe = criteria.get(EntityConstants.Category.categoryId);
		if (categoryIdSe != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Category.categoryId), categoryIdSe));
		List categoryIds = (List) criteria.get(EntityConstants.Category.categoryIds);
		if (categoryIds != null && !categoryIds.isEmpty())
			predicateList.add(criteriaRoot.get(EntityConstants.Category.categoryId).in(categoryIds));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setCategoryId(((Number) data.get(EntityConstants.Category.categoryId)).shortValue());
	}

	@Override
	public Object getEntityId()
	{
		return getCategoryId();
	}
}
