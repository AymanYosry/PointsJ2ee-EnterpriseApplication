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
 * The persistent class for the function_type database table.
 */
@Entity
@Table(name = "function_type")
@NamedQuery(name = "FunctionType.findAll", query = "SELECT f FROM FunctionType f")
public class FunctionType extends AbsoluteEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.FunctionType.functionTypeId };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "function_type_id", unique = true, nullable = false)
	private short functionTypeId;

	@Column(name = "function_type_name", nullable = false, length = 45)
	private String functionTypeName;

	// // bi-directional many-to-one association to ItemStatus
	// @OneToMany(mappedBy = "functionType")
	// private List<ItemStatus> itemStatuses;

	// bi-directional many-to-one association to MessageCenter
	@OneToMany(mappedBy = "functionType")
	private List<MessageCenter> messageCenters;

	public FunctionType()
	{
	}

	public short getFunctionTypeId()
	{
		return this.functionTypeId;
	}

	public void setFunctionTypeId(short functionTypeId)
	{
		this.functionTypeId = functionTypeId;
	}

	public String getFunctionTypeName()
	{
		return this.functionTypeName;
	}

	public void setFunctionTypeName(String functionTypeName)
	{
		this.functionTypeName = functionTypeName;
	}
	//
	// public List<ItemStatus> getItemStatuses()
	// {
	// return this.itemStatuses;
	// }
	//
	// public void setItemStatuses(List<ItemStatus> itemStatuses)
	// {
	// this.itemStatuses = itemStatuses;
	// }
	//
	// public ItemStatus addItemStatus(ItemStatus itemStatus)
	// {
	// getItemStatuses().add(itemStatus);
	// itemStatus.setFunctionType(this);
	//
	// return itemStatus;
	// }
	//
	// public ItemStatus removeItemStatus(ItemStatus itemStatus)
	// {
	// getItemStatuses().remove(itemStatus);
	// itemStatus.setFunctionType(null);
	//
	// return itemStatus;
	// }

	public List<MessageCenter> getMessageCenters()
	{
		return this.messageCenters;
	}

	public void setMessageCenters(List<MessageCenter> messageCenters)
	{
		this.messageCenters = messageCenters;
	}

	public MessageCenter addMessageCenter(MessageCenter messageCenter)
	{
		getMessageCenters().add(messageCenter);
		messageCenter.setFunctionType(this);

		return messageCenter;
	}

	public MessageCenter removeMessageCenter(MessageCenter messageCenter)
	{
		getMessageCenters().remove(messageCenter);
		messageCenter.setFunctionType(null);

		return messageCenter;
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
		map.put(EntityConstants.FunctionType.functionTypeId, functionTypeId);
		map.put(EntityConstants.FunctionType.functionTypeName, functionTypeName);
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setFunctionTypeName((String) data.get(EntityConstants.FunctionType.functionTypeName));
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot, Map<String, Object> criteria)
			throws EntityException
	{
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Object functionTypeIdSe = criteria.get(EntityConstants.FunctionType.functionTypeId);
		if (functionTypeIdSe != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.FunctionType.functionTypeId), functionTypeIdSe));
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		setFunctionTypeId(((Number) data.get(EntityConstants.FunctionType.functionTypeId)).shortValue());
	}

	@Override
	public Object getEntityId()
	{
		return getFunctionTypeId();
	}
}
