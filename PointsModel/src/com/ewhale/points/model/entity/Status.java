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
 * The persistent class for the status database table.
 */
@Entity
@Table(name = "status")
@NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")
public class Status extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
			{ EntityConstants.Status.statusId };
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id", unique = true, nullable = false)
	private short statusId;

	@Column(name = "status_name", nullable = false, length = 45)
	private String statusName;

	// bi-directional many-to-one association to ItemStatus
	@OneToMany(mappedBy = "status")
	private List<ItemStatus> itemStatuses;
//
//	// bi-directional many-to-many association to Agent
//	@OneToMany(mappedBy = "status")
//	private List<ItemStatus> agents;
//
//	// bi-directional many-to-many association to Contract
//	@OneToMany(mappedBy = "status")
//	private List<Contract> contracts;
//
//	// bi-directional many-to-many association to Product
//	@OneToMany(mappedBy = "status")
//	private List<Product> products;
//
//	// bi-directional many-to-many association to Profile
//	@OneToMany(mappedBy = "status")
//	private List<Profile> profiles;
//
//	// bi-directional many-to-many association to Promotion
//	@OneToMany(mappedBy = "status")
//	private List<Promotion> promotions;

	
	public Status()
	{
	}

	public short getStatusId()
	{
		return this.statusId;
	}

	public void setStatusId(short statusId)
	{
		this.statusId = (byte)statusId;
	}

	public String getStatusName()
	{
		return this.statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

	public List<ItemStatus> getItemStatuses()
	{
		return this.itemStatuses;
	}

	public void setItemStatuses(List<ItemStatus> itemStatuses)
	{
		this.itemStatuses = itemStatuses;
	}

	public ItemStatus addItemStatus(ItemStatus itemStatus)
	{
		getItemStatuses().add(itemStatus);
		itemStatus.setStatus(this);

		return itemStatus;
	}

	public ItemStatus removeItemStatus(ItemStatus itemStatus)
	{
		getItemStatuses().remove(itemStatus);
		itemStatus.setStatus(null);

		return itemStatus;
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
		map.put(EntityConstants.Status.statusId, statusId);
		map.put(EntityConstants.Status.statusName, statusName);
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setStatusName((String) data.get(EntityConstants.Status.statusName));
	}
	
	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Object statusIdSe = criteria.get(EntityConstants.Status.statusId);
		if (statusIdSe != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Status.statusId), statusIdSe));
		return predicateList;
	}
	
	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setStatusId(((Number) data.get(EntityConstants.Status.statusId)).shortValue());
	}
	
	@Override
	public Object getEntityId()
	{
		return getStatusId();
	}
}