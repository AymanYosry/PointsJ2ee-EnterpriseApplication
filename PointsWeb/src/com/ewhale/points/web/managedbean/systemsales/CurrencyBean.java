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
public class CurrencyBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short currencyId;

	private String currencyName;

	private String currencySign;

	private Float pointsValue;

	private Float valueEgp;

	public CurrencyBean()
	{
		setHasMoreDetails(false);

	}

	public Short getCurrencyId()
	{
		return currencyId;
	}

	public void setCurrencyId(Short currencyId)
	{
		this.currencyId = currencyId;
	}

	public String getCurrencyName()
	{
		return currencyName;
	}

	public void setCurrencyName(String currencyName)
	{
		this.currencyName = currencyName;
	}

	public String getCurrencySign()
	{
		return currencySign;
	}

	public void setCurrencySign(String currencySign)
	{
		this.currencySign = currencySign;
	}

	public Float getPointsValue()
	{
		return pointsValue;
	}

	public void setPointsValue(Float pointsValue)
	{
		this.pointsValue = pointsValue;
	}

	public Float getValueEgp()
	{
		return valueEgp;
	}

	public void setValueEgp(Float valueEgp)
	{
		this.valueEgp = valueEgp;
	}

	@Override
	protected void handlePostConstruct()
	{
		viewCurrenciesList();
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> currency = systemSalesServiceClient.currencyDetails(data.get(EntityConstants.Currency.currencyId).toString());
		currencyId = ((Number) currency.get(EntityConstants.Currency.currencyId)).shortValue();
		currencyName = (String) currency.get(EntityConstants.Currency.currencyName);
		currencySign = (String) currency.get(EntityConstants.Currency.currencySign);
		pointsValue = ((Number)currency.get(EntityConstants.Currency.pointsValue)).floatValue();
		valueEgp = ((Number)currency.get(EntityConstants.Currency.valueEgp)).floatValue();
	}
//IMP_Badawy no need here for details page
	@Override
	protected String getUpdatePageName()
	{
		return "currency_details.xhtml";
	}
	@Override
	protected String getDetailsPageName()
	{
		return "currency_details.xhtml";
	}
	public void viewCurrenciesList()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> allCurrenciesCollection = systemSalesServiceClient.getAllCurrencies();
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Currency.currencyId, null },
					{ EntityConstants.Currency.currencyName, null },
					{ EntityConstants.Currency.currencySign, null },
					{ EntityConstants.Currency.pointsValue, null } };
		populateTable(allCurrenciesCollection,columnKeys);
	}

	public void addCurrency()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Currency.currencyName, currencyName);
		data.put(EntityConstants.Currency.currencySign, currencySign);
		data.put(EntityConstants.Currency.pointsValue, pointsValue);
		data.put(EntityConstants.Currency.valueEgp, valueEgp);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.addCurrency(data);
		viewCurrenciesList();
		//IMP_Ahmed get Strings from aplication.properties file
		FacesUtil.growlInfoMessage("Success", "New record has been added successfully");

	}

	public void updateCurrency()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Currency.currencyId, currencyId);
		data.put(EntityConstants.Currency.currencyName, currencyName);
		data.put(EntityConstants.Currency.currencySign, currencySign);
		data.put(EntityConstants.Currency.pointsValue, pointsValue);
		data.put(EntityConstants.Currency.valueEgp, valueEgp);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.updateCurrency(data);
	}

	public void deleteCurrency()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.deleteCurrency(currencyId + "");

	}
	@Override
	protected void resetToAdd()
	{
		// IMP_Ahmed doesn't clear the elements
		setCurrencyId(null);
		setCurrencyName(null);
		setCurrencySign(null);
		setPointsValue(null);
		setValueEgp(null);
	}

}
