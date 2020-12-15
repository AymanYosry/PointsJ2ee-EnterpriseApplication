package com.ewhale.points.web.managedbean.agentadmin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.MessageBean;
import com.ewhale.points.web.managedbean.systemadmin.SysAdminAgentProductBean;
import com.ewhale.points.web.managedbean.systemadmin.SysAdminAgentPromotionBean;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentMessageBean extends MessageBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean unRespondedOnly;

	public boolean isUnRespondedOnly()
	{
		return unRespondedOnly;
	}

	public void setUnRespondedOnly(boolean unRespondedOnly)
	{
		this.unRespondedOnly = unRespondedOnly;
	}

	@Override
	protected void handlePostConstruct()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		setFromRequestDate(cal.getTime());
		setCanAdd(false);
		setCanUpdate(false);
		loadFunctionTypes();
	}

	public void viewMessagesList()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.MessageCenter.agentId, FacesUtil.getLoginAgentId());
		data.put(EntityConstants.MessageCenter.requestDate_From_Search_NAME, getFromRequestDate());
		data.put(EntityConstants.MessageCenter.requestDate_To_Search_NAME, getToRequestDate());
		data.put(EntityConstants.MessageCenter.responseDate_From_Search_NAME, getFromResponseDate());
		data.put(EntityConstants.MessageCenter.responseDate_To_Search_NAME, getToResponseDate());
		data.put(EntityConstants.MessageCenter.functionTypeId, getFunctionTypeId());
		data.put(EntityConstants.MessageCenter.not_responded_messages_Search, unRespondedOnly);
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> messagesDataList = agentAdminServiceClient.getAgentMessagesList(data);
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.MessageCenter.requestDate, null },
					{ EntityConstants.MessageCenter.requestMessage, null },
					{ EntityConstants.MessageCenter.responseDate, null },
					{ EntityConstants.MessageCenter.responseMessage, null },
					{ EntityConstants.MessageCenter.responseEmpFullName, null } };
		populateTable(messagesDataList, columnKeys);
	}

	@Override
	protected String getDetailsPageName()
	{
		return "message_details";
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> messageData = agentAdminServiceClient.messageDetails(data.get(EntityConstants.MessageCenter.messageCenterId) + "");
		fillDetailsData(messageData);
	}

	@Override
	public void viewDetails(Map<String, Object> data, String selectedField)
	{
		long itemId = ((Number) data.get(EntityConstants.MessageCenter.itemId)).longValue();
		long messageCenterId = ((Number) data.get(EntityConstants.MessageCenter.messageCenterId)).longValue();
		// long agentId = ((Number) data.get(EntityConstants.MessageCenter.agentId)).longValue();
		short functionTypeId = ((Number) data.get(EntityConstants.MessageCenter.functionTypeId)).shortValue();
		Map<String, Object> itemData = new HashMap<>();
		if (functionTypeId == EntityConstants.FunctionType.Fixed.activateProductFunction.ID)
		{
			SysAdminAgentProductBean sysAdminAgentProductBean = FacesUtil.getObjectFromSession(SysAdminAgentProductBean.class, true);
			sysAdminAgentProductBean.setMessageCenterId(messageCenterId);
			itemData.put(EntityConstants.Product.productId, itemId);
			sysAdminAgentProductBean.viewDetails(itemData);
		}
		else if (functionTypeId == EntityConstants.FunctionType.Fixed.activatePromotionFunction.ID)
		{
			SysAdminAgentPromotionBean sysAdminAgentPromotionBean = FacesUtil.getObjectFromSession(SysAdminAgentPromotionBean.class, true);
			sysAdminAgentPromotionBean.setMessageCenterId(messageCenterId);
			itemData.put(EntityConstants.Promotion.promotionId, itemId);
			sysAdminAgentPromotionBean.viewDetails(itemData);
		}
	}

}
