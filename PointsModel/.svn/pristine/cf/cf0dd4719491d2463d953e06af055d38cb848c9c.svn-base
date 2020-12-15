package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.model.entity.interfaces.InsertTracking;

/**
 * The persistent class for the points database table.
 */
@Entity
@Table(name = "points")
@NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p")
@NamedNativeQuery(name = "Point.sumAll", query = "SELECT SUM( txn_type*CAST(points_value AS SIGNED))profile_points "
		+ "FROM points p,profile prf WHERE  points_profile_id=profile_id and prf.profile_id = :profileId")
@Inheritance(strategy = InheritanceType.JOINED)
public class Point extends AbsoluteEntity implements Serializable, InsertTracking
{
	private static final long serialVersionUID = 1L;

	public static final String sumProfilePointsNamedQuery = "Point.sumAll";

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Point.pointsId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "points_id", unique = true, nullable = false)
	private long pointsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_date", nullable = false)
	private Date insertDate = new Date();

	@Column(name = "points_value", nullable = false)
	private int pointsValue;

	@Column(name = "txn_type", nullable = false)
	private byte txnType;

	// bi-directional many-to-one association to SysEvent
	@ManyToOne
	@JoinColumn(name = "points_event_id", nullable = false)
	private SysEvent sysEvent;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "ins_emp_id")
	private Profile insEmp;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "points_profile_id", nullable = false)
	private Profile pointsProfile;

	public Point()
	{
	}

	public long getPointsId()
	{
		return this.pointsId;
	}

	public void setPointsId(long pointsId)
	{
		this.pointsId = pointsId;
	}

	public byte getTxnType()
	{
		return this.txnType >= 0 ? (byte) 1 : (byte) -1;
	}

	public void setTxnType(byte txnType)
	{
		this.txnType = txnType >= 0 ? (byte) 1 : (byte) -1;
	}

	public int getPointsValue()
	{
		return this.pointsValue;
	}

	public void setPointsValue(int pointsValue)
	{
		this.pointsValue = pointsValue;
	}

	public Date getInsertDate()
	{
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public SysEvent getSysEvent()
	{
		return this.sysEvent;
	}

	public void setSysEvent(SysEvent sysEvent)
	{
		this.sysEvent = sysEvent;
	}

	public Profile getInsEmp()
	{
		return this.insEmp;
	}

	public void setInsEmp(Profile insEmp)
	{
		this.insEmp = insEmp;
	}

	public Profile getPointsProfile()
	{
		return pointsProfile;
	}

	public void setPointsProfile(Profile pointsProfile)
	{
		this.pointsProfile = pointsProfile;
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
		map.put(EntityConstants.Point.pointsId, pointsId);
		map.put(EntityConstants.Point.pointsValue, pointsValue * txnType);
		map.put(EntityConstants.Point.eventId, sysEvent.getSysEventId());
		map.put(EntityConstants.Point.sysEvent, sysEvent.transformMainDataToMap());
		map.put(EntityConstants.Point.txnType, txnType);
		map.put(EntityConstants.Point.profileId, pointsProfile.getProfileId());
		map.put(EntityConstants.Point.insertDate, getStringFromDate(insertDate));
		map.put(EntityConstants.Point.insEmpId, insEmp.getProfileId());
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot,
			Map<String, Object> criteria) throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Number pointsId = (Number) criteria.get(EntityConstants.Point.pointsId);
		if (pointsId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Point.pointsId), pointsId.longValue()));

		Long insertDate_From = (Long) criteria.get(EntityConstants.Point.insertDate_From_Search);
		if (insertDate_From != null)
			predicateList.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Point.insertDate), getDateFromLong(insertDate_From)));
		Long insertDate_To = (Long) criteria.get(EntityConstants.Point.insertDate_To_Search);
		if (insertDate_To != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.Point.insertDate), getDateFromLong(insertDate_To)));
		Number pointsValueFrom = (Number) criteria.get(EntityConstants.Point.pointsValue_From_Search);
		if (pointsValueFrom != null)
			predicateList.add(criteriaBuilder.greaterThan(criteriaRoot.get(EntityConstants.Point.pointsValue), pointsValueFrom.floatValue()));
		Number pointsValueTo = (Number) criteria.get(EntityConstants.Point.pointsValue_To_Search);
		if (pointsValueTo != null)
			predicateList.add(criteriaBuilder.lessThan(criteriaRoot.get(EntityConstants.Point.pointsValue), pointsValueTo.floatValue()));
		Number profileId = (Number) criteria.get(EntityConstants.Point.profileId);
		if (profileId != null)
		{
			Join<Point, Profile> pointProfileJoin = criteriaRoot.join(EntityConstants.Point.profile);
			predicateList.add(criteriaBuilder.equal(pointProfileJoin.get(EntityConstants.Profile.profileId), profileId.longValue()));
		}

		Number eventId = (Number) criteria.get(EntityConstants.Point.eventId);
		if (eventId != null)
		{
			Join<Point, SysEvent> pointSysEventJoin = criteriaRoot.join(EntityConstants.Point.sysEvent);
			predicateList.add(criteriaBuilder.equal(pointSysEventJoin.get(EntityConstants.SysEvent.sysEventId), eventId.longValue()));
		}

		Number txnType = (Number) criteria.get(EntityConstants.Point.txnType);
		if (txnType != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Point.txnType), txnType.byteValue()));

		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setPointsId(((Number) data.get(EntityConstants.Point.pointsId)).longValue());
	}

	@Override
	public Object getEntityId()
	{
		return getPointsId();
	}

	@Override
	public <T extends AbsoluteEntity> Order[] getDefaultOrderFields(CriteriaBuilder criteriaBuilder, Root<T> criteriaRoot)
	{
		return new Order[]
			{ criteriaBuilder.desc(criteriaRoot.get(EntityConstants.Point.insertDate)) };
	}
}
