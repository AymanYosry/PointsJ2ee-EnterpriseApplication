package com.ewhale.points.web.managedbean.agentadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.InvoiceBean;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentInvoiceBean extends InvoiceBean
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date invoiceDate_from, invoiceDate_to;

	AgentAdminServiceClient agentAdminServiceClient = null;

	@Override
	protected void handlePostConstruct()
	{
		setCanAdd(false);
		setCanUpdate(false);
		setHasMoreDetails(false);
	}

	public Date getInvoiceDate_from()
	{
		return invoiceDate_from;
	}

	public void setInvoiceDate_from(Date invoiceDate_from)
	{
		this.invoiceDate_from = invoiceDate_from;
	}

	public Date getInvoiceDate_to()
	{
		return invoiceDate_to;
	}

	public void setInvoiceDate_to(Date invoiceDate_to)
	{
		this.invoiceDate_to = invoiceDate_to;
	}

	public void getAgentInvoices()
	{
		agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());

		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.SysInvoice.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		data.put(EntityConstants.SysInvoice.invoiceDate_From_Search, invoiceDate_from);
		data.put(EntityConstants.SysInvoice.invoiceDate_To_Search, invoiceDate_to);

		List<Map<String, Object>> invoicesList = agentAdminServiceClient.agentInvoicesList(data);
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.SysInvoice.sysInvoiceId, null },
					{ EntityConstants.SysInvoice.insertDate, null },
					{ EntityConstants.SysInvoice.invoiceValue, null },
					{ EntityConstants.SysInvoice.currency, EntityConstants.Currency.currencyName } };
		String[] linkableLists = new String[]
			{ EntityConstants.SysInvoice.purchases, EntityConstants.SysInvoice.pointsExchanges, EntityConstants.SysInvoice.promotions };
		populateTable(invoicesList, linkableLists, columnKeys);

	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());

		Map<String, Object> invoiceDetails = agentAdminServiceClient.viewInvoiceDetails(data.get(EntityConstants.SysInvoice.sysInvoiceId) + "");
		fillDetailsData(invoiceDetails);
	}

	@Override
	public String viewListDetails(Map<String, Object> data, String selectedField)
	{
		long selectedInvoice = ((Number) data.get(EntityConstants.SysInvoice.sysInvoiceId)).longValue();
		if (selectedField.equals(EntityConstants.SysInvoice.promotions))
		{
			AgentAdminAgentPromotionBean agentAdminAgentPromotionBean = FacesUtil.getObjectFromSession(AgentAdminAgentPromotionBean.class, true);
			agentAdminAgentPromotionBean.setSysInvoiceId(selectedInvoice);
			agentAdminAgentPromotionBean.setParentPageTitle("" + selectedInvoice);
			agentAdminAgentPromotionBean.setParentPage("invoice_search");
			agentAdminAgentPromotionBean.getAgentPromotions();
			return "promotion_search";
		}
		else if (selectedField.equals(EntityConstants.SysInvoice.purchases))
		{
			AgentAdminAgentPurchaseBean agentAdminAgentPurchaseBean = FacesUtil.getObjectFromSession(AgentAdminAgentPurchaseBean.class, true);
			agentAdminAgentPurchaseBean.setSysInvoiceId(selectedInvoice);
			agentAdminAgentPurchaseBean.setParentPageTitle("" + selectedInvoice);
			agentAdminAgentPurchaseBean.setParentPage("invoice_search");
			agentAdminAgentPurchaseBean.getPurchasesList();
			return "purchase_search";
		}
		else if (selectedField.equals(EntityConstants.SysInvoice.pointsExchanges))
		{
			AgentAdminAgentExchangeBean agentAdminAgentExchangeBean = FacesUtil.getObjectFromSession(AgentAdminAgentExchangeBean.class, true);
			agentAdminAgentExchangeBean.setSysInvoiceId(selectedInvoice);
			agentAdminAgentExchangeBean.setParentPageTitle("" + selectedInvoice);
			agentAdminAgentExchangeBean.setParentPage("invoice_search");
			agentAdminAgentExchangeBean.getAgentExchangesList();
			return "exchange_search";
		}
		return null;
	}

	@Override
	public void resetParentPage()
	{
		setInvoiceId(null);
		super.resetParentPage();
	}
}
