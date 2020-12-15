package com.ewhale.points.web.managedbean.systemsales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.AbsoluteBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.system.proxy.SystemSalesServiceClient;

@SessionScoped
@ManagedBean
public class CountryBean extends AbsoluteBean
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Byte countryId;

	private String countryName;

	private Short currencyId;

	private String currencyName;

	private List<Map<String, Object>> currenciesList;

	public CountryBean()
	{
		setHasMoreDetails(false);

	}

	public String getCountryName()
	{
		return countryName;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}

	public Byte getCountryId()
	{
		return countryId;
	}

	public void setCountryId(Byte countryId)
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


	public List<Map<String, Object>> getCurrenciesList()
	{
		return currenciesList;
	}

	public void setCurrenciesList(List<Map<String, Object>> currenciesList)
	{
		this.currenciesList = currenciesList;
	}

	public String getCurrencyName()
	{
		return currencyName;
	}

	public void setCurrencyName(String currencyName)
	{
		this.currencyName = currencyName;
	}

	@Override
	protected void handlePostConstruct()
	{
		loadCurrenciesList();
		viewCountriesList();
	}

	@Override
	protected String getUpdatePageName()
	{
		return "country_details.xhtml";
	}

	protected String getDetailsPageName()
	{
		return null;// has no more details "country_details.xhtml";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		countryId = ((Number) data.get(EntityConstants.Country.countryId)).byteValue();
		countryName = (String) data.get(EntityConstants.Country.countryName);
		currencyId = ((Number) ((Map<String, Object>) data.get(EntityConstants.Country.currency)).get(EntityConstants.Currency.currencyId))
				.shortValue();
		currencyName = ((String) ((Map<String, Object>) data.get(EntityConstants.Country.currency)).get(EntityConstants.Currency.currencyName));
	}

	/**
	 * @param data the full data of the record in the table
	 * @param selectedField the name of the field of the linkable object
	 *            this method should check on the selectedField to know wich object is needed to show its details if there is more than one
	 *            then from the data get the appropriate map and call the appropriate bean and call the appropriate page
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void viewDetails(Map<String, Object> data, String selectedField)
	{
		if (selectedField.equals(EntityConstants.Currency.currencyName))
		{
			CurrencyBean currencyBean = FacesUtil.getObjectFromSession(CurrencyBean.class, true);
			currencyBean.viewDetails((Map<String, Object>) data.get(EntityConstants.Country.currency));
		}
	}

	@Override
	public String viewListDetails(Map<String, Object> data, String selectedField)
	{
		if (selectedField.equals(EntityConstants.Country.states))
		{
			byte selectedCountryId = ((Number) data.get(EntityConstants.Country.countryId)).byteValue();
			StateBean stateBean = FacesUtil.getObjectFromSession(StateBean.class, true);
			stateBean.setCountryId(selectedCountryId);
			stateBean.viewStatesList();
			return "state_search.xhtml";
		}
		return null;
	}

	public void viewCountriesList()
	{

		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> listOfCountries = systemSalesServiceClient.getAllCountries();
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Country.countryName, null },
					{ EntityConstants.Country.currency, EntityConstants.Currency.currencyName },
					{ EntityConstants.Country.countryId, null } };
		String[] linkableLists = new String[]
			{ EntityConstants.Country.states};
		populateTable(listOfCountries,linkableLists, columnKeys);
	}

	public void addCountry()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Country.countryId, countryId);
		data.put(EntityConstants.Country.countryName, countryName);
		data.put(EntityConstants.Country.currencyId, currencyId);

		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.addCountry(data);
		viewCountriesList();
		//IMP_Ahmed get Strings from aplication.properties file
		FacesUtil.growlInfoMessage("Success", "New record has been added successfully");
	}

	public void updateCountry()
	{
		Map<String, Object> data = new HashMap<>();
		// IMP_Ahmed make countryId can be updated
		data.put(EntityConstants.Country.countryId, countryId);
		data.put(EntityConstants.Country.countryName, countryName);
		data.put(EntityConstants.Country.currencyId, currencyId);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.updateCountry(data);

	}

	public void deleteCountry()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.deleteCountry(countryId + "");
	}

	private void loadCurrenciesList()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		currenciesList = systemSalesServiceClient.getAllCurrencies();
	}

	@Override
	protected void resetToAdd()
	{
		setCountryId(null);
		setCountryName(null);
		setCurrencyId(null);
		setCurrencyName(null);
	}

}
