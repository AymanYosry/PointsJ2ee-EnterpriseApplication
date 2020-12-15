package com.ewhale.points.web.managedbean.main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;

import com.ewhale.points.common.util.EntityConstants;

public class PurchaseBean extends AbsoluteBean
{
	protected Logger LOG = Logger.getLogger(PurchaseBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long purchaseId, agentId, profileId, sysInvoiceId;

	private Long agentInvoiceNumber;

	private Float agentInvoiceValue;

	private Float discPercent;

	private Float profitValue;

	protected String mobile;

	private String profileFullName;

	private String tradeMark;

	private String branchName;

	private String insertDateStr;

	private String qrCode;

	protected Date purchaseDate_from, purchaseDate_to;

	private Integer pointsValue;

	public PurchaseBean()
	{

	}

	public Long getPurchaseId()
	{
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId)
	{
		this.purchaseId = purchaseId;
	}

	public Long getSysInvoiceId()
	{
		return sysInvoiceId;
	}

	public void setSysInvoiceId(Long sysInvoiceId)
	{
		this.sysInvoiceId = sysInvoiceId;
	}

	public Long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(Long agentId)
	{
		this.agentId = agentId;
	}

	public Long getProfileId()
	{
		return profileId;
	}

	public void setProfileId(Long profileId)
	{
		this.profileId = profileId;
	}

	public Long getAgentInvoiceNumber()
	{
		return agentInvoiceNumber;
	}

	public void setAgentInvoiceNumber(Long agentInvoiceNumber)
	{
		this.agentInvoiceNumber = agentInvoiceNumber;
	}

	public Float getAgentInvoiceValue()
	{
		return agentInvoiceValue;
	}

	public void setAgentInvoiceValue(Float agentInvoiceValue)
	{
		this.agentInvoiceValue = agentInvoiceValue;
	}

	public Float getDiscPercent()
	{
		return discPercent;
	}

	public void setDiscPercent(Float discPercent)
	{
		this.discPercent = discPercent;
	}

	public Float getProfitValue()
	{
		return profitValue;
	}

	public void setProfitValue(Float profitValue)
	{
		this.profitValue = profitValue;
	}

	public Date getPurchaseDate_from()
	{
		return purchaseDate_from;
	}

	public void setPurchaseDate_from(Date purchaseDate_from)
	{
		this.purchaseDate_from = purchaseDate_from;
	}

	public Date getPurchaseDate_to()
	{
		return purchaseDate_to;
	}

	public void setPurchaseDate_to(Date purchaseDate_to)
	{
		this.purchaseDate_to = purchaseDate_to;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		LOG.debug("---- mobile= " + mobile);
		this.mobile = mobile;
	}

	public String getProfileFullName()
	{
		return profileFullName;
	}

	public String getTradeMark()
	{
		return tradeMark;
	}

	public void setTradeMark(String tradeMark)
	{
		this.tradeMark = tradeMark;
	}

	public String getBranchName()
	{
		return branchName;
	}

	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	public Integer getPointsValue()
	{
		return pointsValue;
	}

	public void setPointsValue(Integer pointsValue)
	{
		this.pointsValue = pointsValue;
	}

	public String getQrCode()
	{
		return qrCode;
	}

	public void setQrCode(String qrCode)
	{
		this.qrCode = qrCode;
	}

	public String getInsertDateStr()
	{
		return insertDateStr;
	}

	public void setInsertDateStr(String insertDateStr)
	{
		this.insertDateStr = insertDateStr;
	}

	protected Map<String, Object> fillUserPurchaseDataMap()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Purchase.agentInvoiceNumber, agentInvoiceNumber);
		data.put(EntityConstants.Purchase.agentInvoiceValue, agentInvoiceValue);
		data.put(EntityConstants.Purchase.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		data.put(EntityConstants.Purchase.branchId, FacesUtil.getLoginData().get(EntityConstants.Login.branchId));
		data.put(EntityConstants.Purchase.insEmpId, FacesUtil.getLoginId());
		data.put(EntityConstants.Purchase.profile_mobile, mobile);
		return data;
	}

	@SuppressWarnings("unchecked")
	protected void fillDetailsData(Map<String, Object> purchaseDetails)
	{
		purchaseId = ((Number) purchaseDetails.get(EntityConstants.Purchase.purchaseId)).longValue();
		agentId = ((Number) purchaseDetails.get(EntityConstants.Purchase.agentId)).longValue();
		agentInvoiceNumber = ((Number) purchaseDetails.get(EntityConstants.Purchase.agentInvoiceNumber)).longValue();
		agentInvoiceValue = ((Number) purchaseDetails.get(EntityConstants.Purchase.agentInvoiceValue)).floatValue();
		pointsValue = ((Number) purchaseDetails.get(EntityConstants.Purchase.pointsValue)).intValue();
		profileFullName = ((String) ((Map<String, Object>) purchaseDetails.get(EntityConstants.Purchase.profile))
				.get(EntityConstants.Profile.fullName));
		tradeMark = ((String) ((Map<String, Object>) purchaseDetails.get(EntityConstants.Purchase.agent)).get(EntityConstants.Agent.tradeMark));
		branchName = ((String) ((Map<String, Object>) purchaseDetails.get(EntityConstants.Purchase.branch)).get(EntityConstants.Branch.branchName));
		qrCode = (String) purchaseDetails.get(EntityConstants.Purchase.qrCode);
		insertDateStr = (String) purchaseDetails.get(EntityConstants.Purchase.insertDate);
	}

	@Override
	public String getRowStyleClass(Map<String, Object> data)
	{
		if (!(Boolean) data.get(EntityConstants.Purchase.fund))
			return "redRow";
		else
			return null;
	}

	@Override
	protected void resetToAdd()
	{
		purchaseId = null;
		agentId = null;
		agentInvoiceNumber = null;
		agentInvoiceValue = null;
		pointsValue = null;
		profileFullName = null;
		tradeMark = null;
		branchName = null;
		profileId = null;
		sysInvoiceId = null;
		discPercent = null;
		profitValue = null;
		mobile = null;
		qrCode = null;
	}
}
