package com.ewhale.points.controller.facade;

import java.util.Date;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.ItemStatusFacade;
import com.ewhale.points.model.entity.MessageCenter;
import com.ewhale.points.model.entity.Profile;
import com.ewhale.points.model.entity.Status;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class ItemStatusFacadeBean
 */
@Stateless
@Local(ItemStatusFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ItemStatusFacadeBean extends AbsoluteFacadeBean implements ItemStatusFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.ITEMSTATUS) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
		Status status = new Status();
		short statusId = ((Number) data.remove(EntityConstants.ItemStatus.statusId)).shortValue();
		status.setStatusId(statusId);
		data.put(EntityConstants.ItemStatus.status, status);
		data.put(EntityConstants.ItemStatus.statusDate, new Date());

		if (data.get(EntityConstants.ItemStatus.updateStatusEmpId) != null)
		{
			Profile updateEmp = new Profile();
			updateEmp.setEntityId(data.remove(EntityConstants.ItemStatus.updateStatusEmpId));
			data.put(EntityConstants.ItemStatus.updateStatusEmp, updateEmp);
		}
		updateMessageCenter(data);
	}

	protected void updateMessageCenter(Map<String, Object> data) throws EntityException
	{
		Number messageCenterId = (Number) data.remove(EntityConstants.MessageCenter.messageCenterId);
		String responseMessage = (String) data.remove(EntityConstants.MessageCenter.responseMessage);
		if (messageCenterId != null)
		{
			MessageCenter messageCenter = em.viewRecordDetails(MessageCenter.class, messageCenterId.longValue());
			messageCenter.setResponseDate(new Date());
			messageCenter.setResponseEmp((Profile) data.get(EntityConstants.ItemStatus.updateStatusEmp));
			Status status = ((Status) data.get(EntityConstants.ItemStatus.status));
			short statusId = status.getStatusId();
			String responceMessage = (statusId == EntityConstants.Status.Fixed.activeStatus.ID) ? "activated"
					: ((statusId == EntityConstants.Status.Fixed.pendingStatus.ID) ? "refused : " + responseMessage
							: ((statusId == EntityConstants.Status.Fixed.blockedStatus.ID) ? "blocked" : "ERROR"));
			messageCenter.setResponseMessage(responceMessage);
			em.updateRecord(messageCenter);
		}
	}
}
