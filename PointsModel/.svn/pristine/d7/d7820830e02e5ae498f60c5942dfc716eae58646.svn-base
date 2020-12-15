package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the item_status database table.
 */
@Entity
@Table(name = "item_status")
@NamedQuery(name = "ItemStatus.findAll", query = "SELECT i FROM ItemStatus i")
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemStatus extends AbsoluteEntity implements Serializable
{
	//
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.ItemStatus.itemId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_status_id", unique = true, nullable = false)
	private long itemId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "status_date", nullable = false)
	private Date statusDate = new Date();

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "item_status_emp_id", nullable = true)
	private Profile updateStatusEmp;

	// bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_status_status_id", nullable = false)
	private Status status;

	public ItemStatus()
	{
	}

	public long getItemId()
	{
		return this.itemId;
	}

	public void setItemId(long itemId)
	{
		this.itemId = itemId;
	}

	public Date getStatusDate()
	{
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate)
	{
		this.statusDate = statusDate;
	}

	public Profile getUpdateStatusEmp()
	{
		return updateStatusEmp;
	}

	public void setUpdateStatusEmp(Profile updateStatusEmp)
	{
		this.updateStatusEmp = updateStatusEmp;
	}

	public Status getStatus()
	{
		return this.status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
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
		Map<String, Object> map = new HashMap<>();
		map.put(EntityConstants.ItemStatus.itemId, itemId);
		map.put(EntityConstants.ItemStatus.statusId, status.getStatusId());
		map.put(EntityConstants.ItemStatus.statusName, status.getStatusName());
		map.put(EntityConstants.ItemStatus.statusDate, statusDate);
		if (updateStatusEmp != null)
		{
			map.put(EntityConstants.ItemStatus.updateStatusEmpId, updateStatusEmp.getProfileId());
			map.put(EntityConstants.ItemStatus.updateStatusEmpFullName, updateStatusEmp.getFullName());
		}
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);
		// Status status = new Status();
		// status.setStatusId(((Number) data.get(EntityConstants.ItemStatus.statusId)).shortValue());
		// setStatus(status);
		// Profile profile = new Profile();
		// profile.setProfileId(((Number) data.get(EntityConstants.ItemStatus.updateEmpId)).longValue());
		// setUpdateEmp(profile);
		// setStatusDate(getDateFromLong((Long) data.get(EntityConstants.ItemStatus.statusDate)));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<>();
		Object itemId = criteria.get(EntityConstants.ItemStatus.itemId);
		if (itemId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.ItemStatus.itemId), itemId));
		Number statusId = ((Number) criteria.get(EntityConstants.ItemStatus.statusId));
		if (statusId != null)
		{
			Join<ItemStatus, Status> statusJoin = criteriaRoot.join(EntityConstants.ItemStatus.status);
			predicateList.add(criteriaBuilder.equal(statusJoin.get(EntityConstants.ItemStatus.statusId), statusId.shortValue()));
		}
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setItemId(((Number) data.get(EntityConstants.ItemStatus.itemId)).longValue());
	}

	@Override
	public Object getEntityId()
	{
		return getItemId();
	}

	@Override
	public void setEntityId(Object idObj)
	{
		long id = ((Number) idObj).longValue();
		setItemId(id);
	}
}
