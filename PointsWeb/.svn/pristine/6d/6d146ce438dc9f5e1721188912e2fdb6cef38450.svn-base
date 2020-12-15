package com.ewhale.points.web.managedbean.agentseller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.PurchaseBean;
import com.ewhale.points.ws.agent.proxy.AgentSellerServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentSellerPurchaseBean extends PurchaseBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void handlePostConstruct()
	{
		setCanUpdate(false);
		setHasMoreDetails(false);
	}
	
	public void mobileChanged(ValueChangeEvent e)
	{
		setMobile(e.getNewValue().toString());
	}

	public void resetUserPurchase()
	{
		setMobile(null);
		setAgentId(null);
		setAgentInvoiceNumber(null);
		setAgentInvoiceValue(null);
		setProfileId(null);
		setPurchaseId(null);
		setQrCode(null);
	}

	public String fundUserPurchase()
	{
		Map<String, Object> data = fillUserPurchaseDataMap();
		try
		{
			AgentSellerServiceClient agentSellerServiceClient = ServiceClientUtil.getAgentSellerServiceClient(FacesUtil.getLoginToken(), ServiceClientUtil.WEB);
			fillDetailsData(agentSellerServiceClient.fundUserPurchase(data));
			FacesUtil.growlInfoMessage("Success", "Purchase has been added");
			return "";
		}
		catch (Exception e)
		{
			FacesUtil.addErrorMessage("Error", e.getMessage());
			return null;
		}

	}

	public String refundUserPurchase()
	{
		Map<String, Object> data = fillUserPurchaseDataMap();
		AgentSellerServiceClient agentSellerServiceClient = ServiceClientUtil.getAgentSellerServiceClient(FacesUtil.getLoginToken(), ServiceClientUtil.WEB);
		fillDetailsData(agentSellerServiceClient.refundUserPurchase(data));
		resetToAdd();
		FacesUtil.growlInfoMessage("Success", "Re-Fund Purchase has been done");
		return "";

	}

	public void getUserPurchasesList()
	{
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Purchase.purchaseId, null },
					{ EntityConstants.Purchase.insertDate, null },
					{ EntityConstants.Purchase.agentInvoiceNumber, null },
					{ EntityConstants.Purchase.agentInvoiceValue, null },
					{ EntityConstants.Purchase.branch, EntityConstants.Branch.branchName } };
		if (getMobile() == null)
		{
			populateTable(new ArrayList<>(), columnKeys);
			return;
		}
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Purchase.agentId, FacesUtil.getLoginData().get(EntityConstants.Login.agentId));
		data.put(EntityConstants.Purchase.profile_mobile, getMobile());
		AgentSellerServiceClient agentSellerServiceClient = ServiceClientUtil.getAgentSellerServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> purchasesList = agentSellerServiceClient.userPurchasesList(data);

		populateTable(purchasesList, columnKeys);
		// LOG.debug(exchangesList.size());
	}

	public void setRefundData(Map<String, Object> purchaseDetails)
	{
		setAgentInvoiceNumber(((Number) purchaseDetails.get(EntityConstants.Purchase.agentInvoiceNumber)).longValue());
		setAgentInvoiceValue(((Number) purchaseDetails.get(EntityConstants.Purchase.agentInvoiceValue)).floatValue());
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		AgentSellerServiceClient agentSellerServiceClient = ServiceClientUtil.getAgentSellerServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> purchaseDetails = agentSellerServiceClient.viewPurchaseDetails(data.get(EntityConstants.Purchase.purchaseId) + "");
		fillDetailsData(purchaseDetails);
	}
	public String goToIndexPage()
	{
		resetToAdd();
		return "/pages/agent/seller/index.xhtml";
	}
	public String goToAddFund()
	{
		resetToAdd();
		return "/pages/agent/seller/purchase_fund.xhtml";
	}

	public String goToAddReFund()
	{
		resetToAdd();
		return "/pages/agent/seller/purchase_refund.xhtml";
	}

	@Override
	protected void resetToAdd()
	{
		setTableData(null);
		super.resetToAdd();
	}
}
