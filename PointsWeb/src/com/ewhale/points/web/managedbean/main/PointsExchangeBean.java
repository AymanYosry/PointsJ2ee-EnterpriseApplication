package com.ewhale.points.web.managedbean.main;

import java.util.Map;

import com.ewhale.points.common.util.EntityConstants;

public class PointsExchangeBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pointsExchangeId, agentId;

	private Float priceValue;

	private Short currencyId;

	private Long sysInvoiceId;

	private Long productId;

	private Long profileId;

	private String profileMobile;

	private String profileFullName;

	private String tradeMark;

	private String branchName;

	private Integer pointsValue;

	private String productName, productPhoto;

	public PointsExchangeBean()
	{

	}

	public Long getPointsExchangeId()
	{
		return pointsExchangeId;
	}

	public void setPointsExchangeId(Long pointsExchangeId)
	{
		this.pointsExchangeId = pointsExchangeId;
	}

	public Float getPriceValue()
	{
		return priceValue;
	}

	public void setPriceValue(Float priceValue)
	{
		this.priceValue = priceValue;
	}

	public Short getCurrencyId()
	{
		return currencyId;
	}

	public void setCurrencyId(Short currencyId)
	{
		this.currencyId = currencyId;
	}

	public Long getSysInvoiceId()
	{
		return sysInvoiceId;
	}

	public void setSysInvoiceId(Long sysInvoiceId)
	{
		this.sysInvoiceId = sysInvoiceId;
	}

	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(Long productId)
	{
		this.productId = productId;
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

	public String getProfileMobile()
	{
		return profileMobile;
	}

	public void setProfileMobile(String profileMobile)
	{
		this.profileMobile = profileMobile;
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

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getProductPhoto()
	{
		return productPhoto;
	}

	public void setProductPhoto(String productPhoto)
	{
		this.productPhoto = productPhoto;
	}

	@SuppressWarnings("unchecked")
	protected void fillDetailsData(Map<String, Object> data)
	{
		pointsExchangeId = ((Number) data.get(EntityConstants.PointsExchange.pointsExchangeId)).longValue();
		priceValue = ((Number) data.get(EntityConstants.PointsExchange.priceValue)).floatValue();
		agentId = ((Number) data.get(EntityConstants.PointsExchange.agentId)).longValue();
		currencyId = ((Number) data.get(EntityConstants.PointsExchange.currencyId)).shortValue();
		sysInvoiceId = ((Number) data.get(EntityConstants.PointsExchange.invoiceId)).longValue();
		productId = ((Number) data.get(EntityConstants.PointsExchange.productId)).longValue();
		profileId = ((Number) data.get(EntityConstants.PointsExchange.profileId)).longValue();
		profileFullName = ((String) ((Map<String, Object>) data.get(EntityConstants.PointsExchange.profile)).get(EntityConstants.Profile.fullName));
		tradeMark = ((String) ((Map<String, Object>) data.get(EntityConstants.PointsExchange.agent)).get(EntityConstants.Agent.tradeMark));
		branchName = ((String) ((Map<String, Object>) data.get(EntityConstants.PointsExchange.branch)).get(EntityConstants.Branch.branchName));
		productName = ((String) ((Map<String, Object>) data.get(EntityConstants.PointsExchange.product)).get(EntityConstants.Product.productName));
		pointsValue = ((Number) data.get(EntityConstants.PointsExchange.pointsValue)).intValue();
	}

	@Override
	protected void resetToAdd()
	{
		pointsExchangeId = null;
		priceValue = null;
		currencyId = null;
		sysInvoiceId = null;
		productId = null;
		profileId = null;
		profileFullName = null;
		profileMobile = null;
		tradeMark = null;
		branchName = null;
		productName = null;
		productPhoto = null;
		pointsValue = 0;
	}
	@Override
	public String getRowStyleClass(Map<String, Object> data)
	{
		if ((Boolean) data.get(EntityConstants.PointsExchange.exchange))
			return "redRow";
		else
			return null;
	}

}
