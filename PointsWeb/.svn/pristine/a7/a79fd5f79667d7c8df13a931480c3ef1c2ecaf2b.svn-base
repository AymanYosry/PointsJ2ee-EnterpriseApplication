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
public class PaymentMethodBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short paymentMethodId;

	private String paymentMethodName;

	public PaymentMethodBean()
	{
		setHasMoreDetails(false);
	}

	public Short getPaymentMethodId()
	{
		return paymentMethodId;
	}

	public void setPaymentMethodId(Short paymentMethodId)
	{
		this.paymentMethodId = paymentMethodId;
	}

	public String getPaymentMethodName()
	{
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName)
	{
		this.paymentMethodName = paymentMethodName;
	}

	@Override
	protected void handlePostConstruct()
	{
		viewPaymentMethods();
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		paymentMethodId = ((Number) data.get(EntityConstants.PaymentMethod.paymentMethodId)).shortValue();
		paymentMethodName = (String) data.get(EntityConstants.PaymentMethod.paymentMethodName);
	}

	@Override
	protected String getDetailsPageName()
	{
		return null; // has no more details "method_add.xhtml";
	}

	@Override
	protected String getUpdatePageName()
	{
		return "method_details.xhtml";
	}

	public void viewPaymentMethods()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> allPaymentMethodsCollection = systemSalesServiceClient.getAllPaymentMethods();
		populateTable(allPaymentMethodsCollection, EntityConstants.PaymentMethod.paymentMethodId, EntityConstants.PaymentMethod.paymentMethodName);
	}

	public void addPaymentMethod()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.PaymentMethod.paymentMethodName, paymentMethodName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.addPaymentMethod(data);

	}

	public void updatePaymentMethod()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.PaymentMethod.paymentMethodId, paymentMethodId);
		data.put(EntityConstants.PaymentMethod.paymentMethodName, paymentMethodName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.updatePaymentMethod(data);
		closeDialoge();
	}

	public void deletePaymentMethod()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.deletePaymentMethod(paymentMethodId + "");

	}

	@Override
	protected void resetToAdd()
	{
		setPaymentMethodId(null);
		setPaymentMethodName(null);
	}

}
