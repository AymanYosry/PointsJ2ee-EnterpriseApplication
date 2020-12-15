package com.ewhale.points.web.managedbean.main;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.ws.main.proxy.LookUpServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

public class MessageBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long agentId;

	private Long messageId, requestAgentEmpId, responseEmpId;

	private Short functionTypeId;

	private Long messageCenterItemId;

	private String requestDate, responseDate;

	private String requestMessage, responseMessage;

	private String requestAgentEmpFullName, responseEmpFullName;

	private Date fromRequestDate, toRequestDate, toResponseDate, fromResponseDate;

	private boolean unRespondedOnly;

	private List<Map<String, Object>> functionTypes;

	public MessageBean()
	{

	}

	public List<Map<String, Object>> getFunctionTypes()
	{
		return functionTypes;
	}

	public Long getMessageId()
	{
		return messageId;
	}

	public void setMessageId(Long messageId)
	{
		this.messageId = messageId;
	}

	public Long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(Long agentId)
	{
		this.agentId = agentId;
	}

	public Long getRequestAgentEmpId()
	{
		return requestAgentEmpId;
	}

	public void setRequestAgentEmpId(Long requestAgentEmpId)
	{
		this.requestAgentEmpId = requestAgentEmpId;
	}

	public Long getResponseEmpId()
	{
		return responseEmpId;
	}

	public void setResponseEmpId(Long responseEmpId)
	{
		this.responseEmpId = responseEmpId;
	}

	public Short getFunctionTypeId()
	{
		return functionTypeId;
	}

	public void setFunctionTypeId(Short functionTypeId)
	{
		this.functionTypeId = functionTypeId;
	}

	public Long getMessageCenterItemId()
	{
		return messageCenterItemId;
	}

	public void setMessageCenterItemId(Long messageCenterItemId)
	{
		this.messageCenterItemId = messageCenterItemId;
	}

	public String getRequestMessage()
	{
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage)
	{
		this.requestMessage = requestMessage;
	}

	public String getResponseMessage()
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}

	public String getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate(String requestDate)
	{
		this.requestDate = requestDate;
	}

	public String getResponseDate()
	{
		return responseDate;
	}

	public void setResponseDate(String responseDate)
	{
		this.responseDate = responseDate;
	}

	public String getRequestAgentEmpFullName()
	{
		return requestAgentEmpFullName;
	}

	public void setRequestAgentEmpFullName(String requestAgentEmpFullName)
	{
		this.requestAgentEmpFullName = requestAgentEmpFullName;
	}

	public String getResponseEmpFullName()
	{
		return responseEmpFullName;
	}

	public void setResponseEmpFullName(String responseEmpFullName)
	{
		this.responseEmpFullName = responseEmpFullName;
	}

	public boolean isUnRespondedOnly()
	{
		return unRespondedOnly;
	}

	public void setUnRespondedOnly(boolean unRespondedOnly)
	{
		this.unRespondedOnly = unRespondedOnly;
	}

	public Date getFromRequestDate()
	{
		return fromRequestDate;
	}

	public void setFromRequestDate(Date fromRequestDate)
	{
		this.fromRequestDate = fromRequestDate;
	}

	public Date getToRequestDate()
	{
		return toRequestDate;
	}

	public void setToRequestDate(Date toRequestDate)
	{
		this.toRequestDate = toRequestDate;
	}

	public Date getToResponseDate()
	{
		return toResponseDate;
	}

	public void setToResponseDate(Date toResponseDate)
	{
		this.toResponseDate = toResponseDate;
	}

	public Date getFromResponseDate()
	{
		return fromResponseDate;
	}

	public void setFromResponseDate(Date fromResponseDate)
	{
		this.fromResponseDate = fromResponseDate;
	}

	protected void loadFunctionTypes()
	{
		LookUpServiceClient lookUpServiceClient = ServiceClientUtil.getLookUpServiceClient(FacesUtil.getLoginToken());
		functionTypes = lookUpServiceClient.getAllBusinessFunctions();
	}
	protected void fillDetailsData(Map<String, Object> messageData)
	{
		messageId = ((Number) messageData.get(EntityConstants.MessageCenter.messageCenterId)).longValue();
		agentId = ((Number) messageData.get(EntityConstants.MessageCenter.agentId)).longValue();
		requestAgentEmpId = ((Number) messageData.get(EntityConstants.MessageCenter.requestAgentEmpId)).longValue();
		functionTypeId = ((Number) messageData.get(EntityConstants.MessageCenter.functionTypeId)).shortValue();
		messageCenterItemId = ((Number) messageData.get(EntityConstants.MessageCenter.itemId)).longValue();
		requestDate = ((String) messageData.get(EntityConstants.MessageCenter.requestDate));
		responseDate = ((String) messageData.get(EntityConstants.MessageCenter.responseDate));
		requestMessage = ((String) messageData.get(EntityConstants.MessageCenter.requestMessage));
		responseMessage = ((String) messageData.get(EntityConstants.MessageCenter.responseMessage));
		requestAgentEmpFullName = ((String) messageData.get(EntityConstants.MessageCenter.requestAgentEmpFullName));
		responseEmpFullName = ((String) messageData.get(EntityConstants.MessageCenter.responseEmpFullName));
	}

	@Override
	public String getRowStyleClass(Map<String, Object> data)
	{
		if (data.get(EntityConstants.MessageCenter.responseDate) == null)
			return "yellowRow";
		else
			return null;
	}
}
