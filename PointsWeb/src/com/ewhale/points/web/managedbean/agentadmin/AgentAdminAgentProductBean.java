package com.ewhale.points.web.managedbean.agentadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.ProductBean;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentProductBean extends ProductBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date validityDate_from, validityDate_to;

	private List<Map<String, Object>> currenciesList;

	// private List<Map<String, Object>> countriesList;

	private List<Map<String, Object>> statusesList;

	private String countryName;

	private String currencyName;

	private Short statusIdSearch;

	public Date getValidityDate_from()
	{
		return validityDate_from;
	}

	public void setValidityDate_from(Date validityDate_from)
	{
		this.validityDate_from = validityDate_from;
	}

	public List<Map<String, Object>> getCurrenciesList()
	{
		return currenciesList;
	}

	//
	// public List<Map<String, Object>> getCountriesList()
	// {
	// return countriesList;
	// }
	//
	// public void setCountriesList(List<Map<String, Object>> countriesList)
	// {
	// this.countriesList = countriesList;
	// }

	public List<Map<String, Object>> getStatusesList()
	{
		return statusesList;
	}

	public Date getValidityDate_to()
	{
		return validityDate_to;
	}

	public void setValidityDate_to(Date validityDate_to)
	{
		this.validityDate_to = validityDate_to;
	}

	public String getCountryName()
	{
		return countryName;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}

	public String getCurrencyName()
	{
		return currencyName;
	}

	public void setCurrencyName(String currencyName)
	{
		this.currencyName = currencyName;
	}

	public Short getStatusIdSearch()
	{
		return statusIdSearch;
	}

	public void setStatusIdSearch(Short statusIdSearch)
	{
		this.statusIdSearch = statusIdSearch;
	}

	@Override
	protected void handlePostConstruct()
	{
		// countriesList = FacesUtil.loadCountriesList();
		currenciesList = FacesUtil.loadCurrenciesList();
		statusesList = FacesUtil.loadStatusesList();
	}

	@Override
	protected String getUpdatePageName()
	{
		return "product_details.xhtml";
	}

	@Override
	protected String getDetailsPageName()
	{
		return "product_details.xhtml";
	}

	public void addAgentProduct() throws Exception
	{
		Map<String, Object> data = fillDataMap();
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		data.put(EntityConstants.Product.insEmpId, FacesUtil.getLoginId());
		data.put(EntityConstants.Product.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		data.put(EntityConstants.Product.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
		agentAdminServiceClient.addAgentProduct(data);
		getAgentProducts();
		FacesUtil.growlInfoMessage("Success", "Added Successfully");
	}

	public void updateAgentProduct() throws Exception
	{
		Map<String, Object> data = fillDataMap();
		data.put(EntityConstants.Product.productId, getProductId());
		data.put(EntityConstants.Product.updateEmpId, FacesUtil.getLoginId());
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		agentAdminServiceClient.updateAgentProduct(data);
		getAgentProducts();
		FacesUtil.growlInfoMessage("Success", "Updated Successfully");
		closeDialoge();
	}

	public void deleteAgentProduct()
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		agentAdminServiceClient.deleteAgentProduct(getProductId() + "");
	}

	public void getAgentProducts()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Product.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		data.put(EntityConstants.Product.validityDate_From_Search, validityDate_from);
		data.put(EntityConstants.Product.validityDate_To_Search, validityDate_to);
		data.put(EntityConstants.Product.statusId, statusIdSearch);
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> agentProductsList = agentAdminServiceClient.getAgentProductsList(data);

		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Product.productName, null },
					{ EntityConstants.Product.validityDate, null },
					{ EntityConstants.Product.pointsValue, null },
					{ EntityConstants.Product.price, null },
					{ EntityConstants.Product.statusName, null } };

		populateTable(agentProductsList, columnKeys);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		// IMP_Badawy the product Id should be got from the data (the map parameter) because it is related to the line at which the click is done
		// do not use the getProductId
		Map<String, Object> productData = agentAdminServiceClient.productDetails(data.get(EntityConstants.Product.productId) + "");
		fillDetailsData(productData);
		// IMP_Badawy if this gave nullPointerException
		// go to entity and put the object in the map (in the transformToMap
		// I made this as an example for you
		currencyName = ((String) ((Map<String, Object>) data.get(EntityConstants.Product.currency)).get(EntityConstants.Currency.currencyName));
		countryName = ((String) ((Map<String, Object>) data.get(EntityConstants.Product.country)).get(EntityConstants.Country.countryName));

	}

	// public void handleFileUpload(FileUploadEvent event)
	// {
	// UploadedFile uploadedPhoto=event.getFile();
	// LOG.debug("-- file size="+uploadedPhoto.getSize()+" -- file content size="+uploadedPhoto.getContents().length);
	// if(uploadedPhoto.getSize()>65500)
	// {
	// FacesUtil.growlErrorMessage("Error", "Image Should not exceed 65KB");
	// return;
	// }
	// setUploadedPhoto(uploadedPhoto);
	// FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	// FacesContext.getCurrentInstance().addMessage(null, message);
	// }

	@Override
	protected void resetToAdd()
	{
		setCountryId(null);
		setCurrencyId(null);
		setProductName(null);
		setProductId(null);
		setValidityDate(null);
		setPrice(null);
		setPhoto(null);
	}

	@Override
	public boolean isCanUpdate(Map<String, Object> data)
	{
		return ((Number)data.get(EntityConstants.Product.statusId)).byteValue()!= EntityConstants.Status.Fixed.activeStatus.ID;
	}
}
