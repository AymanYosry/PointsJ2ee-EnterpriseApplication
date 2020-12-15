package com.ewhale.points.model.entity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MainEntityStatus extends AbsoluteEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// bi-directional many-to-many association to Status
	@ManyToOne
	@JoinTable(name = "item_status", joinColumns =
		{ @JoinColumn(name = "item_id", nullable = false) }, inverseJoinColumns =
		{ @JoinColumn(name = "item_status_status_id", nullable = false) })
	private Status status;

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
}