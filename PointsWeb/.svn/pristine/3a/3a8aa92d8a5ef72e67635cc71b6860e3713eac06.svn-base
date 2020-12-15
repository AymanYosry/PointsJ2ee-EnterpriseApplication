package com.ewhale.points.web.managedbean.main;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

import com.ewhale.points.common.util.EntityConstants;

public class ProductBean extends ItemStatusBean
{
	protected Logger LOG = Logger.getLogger(ProductBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long productId;

	private String photo;

	private UploadedFile uploadedPhoto;

	private Float price;

	private Short pointsValue;

	private String productName;

	private Date validityDate;

	private String validityDateStr;

	private Long agentId;

	private Short countryId;

	private Short currencyId;

	private Long insEmpId;

	private Long updateEmpId;

	public ProductBean()
	{

	}

	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(Long productId)
	{
		this.productId = productId;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public Float getPrice()
	{
		return price;
	}

	public void setPrice(Float price)
	{
		this.price = price;
	}

	public Short getPointsValue()
	{
		return pointsValue;
	}

	public void setPointsValue(Short pointsValue)
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

	public Date getValidityDate()
	{
		return validityDate;
	}

	public void setValidityDate(Date validityDate)
	{
		this.validityDate = validityDate;
	}

	public String getValidityDateStr()
	{
		return validityDateStr;
	}

	public Long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(Long agentId)
	{
		this.agentId = agentId;
	}

	public Short getCountryId()
	{
		return countryId;
	}

	public void setCountryId(Short countryId)
	{
		this.countryId = countryId;
	}

	public Short getCurrencyId()
	{
		return currencyId;
	}

	public void setCurrencyId(Short currencyId)
	{
		this.currencyId = currencyId;
	}

	public Long getInsEmpId()
	{
		return insEmpId;
	}

	public void setInsEmpId(Long insEmpId)
	{
		this.insEmpId = insEmpId;
	}

	public Long getUpdateEmpId()
	{
		return updateEmpId;
	}

	public void setUpdateEmpId(Long updateEmpId)
	{
		this.updateEmpId = updateEmpId;
	}

	public UploadedFile getUploadedPhoto()
	{
		return uploadedPhoto;
	}

	public void setUploadedPhoto(UploadedFile uploadedPhoto)
	{
		LOG.debug("-- file uploaded");
		this.uploadedPhoto = uploadedPhoto;
	}

	protected Map<String, Object> fillDataMap() throws Exception
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Product.photo,
				FacesUtil.isImageUploaded(uploadedPhoto) ? Base64.getEncoder().encodeToString(getUploadedPhoto().getContents()) : null);
		data.put(EntityConstants.Product.price, price);
		data.put(EntityConstants.Product.productName, productName);
		data.put(EntityConstants.Product.validityDate, validityDate);
		data.put(EntityConstants.Product.countryId, countryId);
		data.put(EntityConstants.Product.currencyId, currencyId);
		return data;
	}

	protected void fillDetailsData(Map<String, Object> productData)
	{

		productId = ((Number) productData.get(EntityConstants.Product.productId)).longValue();
		photo = ((String) productData.get(EntityConstants.Product.photo));
		price = ((Number) productData.get(EntityConstants.Product.price)).floatValue();
		pointsValue = ((Number) productData.get(EntityConstants.Product.pointsValue)).shortValue();
		productName = (String) productData.get(EntityConstants.Product.productName);
		validityDateStr=(String) productData.get(EntityConstants.Product.validityDate);
		validityDate = FacesUtil.getDateFromString(validityDateStr);
		countryId = ((Number) productData.get(EntityConstants.Product.countryId)).shortValue();
		currencyId = ((Number) productData.get(EntityConstants.Product.currencyId)).shortValue();
		agentId = ((Number) productData.get(EntityConstants.Product.agentId)).longValue();
	}

	@Override
	protected void resetToAdd()
	{
		setProductId(null);
		setAgentId(null);
		setCountryId(null);
		setCurrencyId(null);
		setPhoto(null);
		setPrice(null);
		setPointsValue(null);
		setProductName(null);
		setValidityDate(null);
	}


}
