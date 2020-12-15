package com.ewhale.points.web.managedbean.agentadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.PurchaseBean;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentPurchaseBean extends PurchaseBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date purchaseDate_from, purchaseDate_to;

	@Override
	protected void handlePostConstruct()
	{
		setCanAdd(false);
		setCanUpdate(false);
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

	public void getPurchasesList()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Purchase.agentId, getAgentId());
		data.put(EntityConstants.Purchase.invoiceId, getSysInvoiceId());
		data.put(EntityConstants.Purchase.insertDate_From_Search, purchaseDate_from);
		data.put(EntityConstants.Purchase.insertDate_To_Search, purchaseDate_to);
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> allPurchasesList = agentAdminServiceClient.agentPurchasesList(data);
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Purchase.insertDate, null },
					{ EntityConstants.Purchase.agentInvoiceNumber, null },
					{ EntityConstants.Purchase.agentInvoiceValue, null },
					{ EntityConstants.Purchase.branch, EntityConstants.Branch.branchName } };
		// IMP_Ahmed calculate ,EntityConstants.Purchase.system_profit
		populateTable(allPurchasesList, columnKeys);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> purchaseData = agentAdminServiceClient.agentPurchaseDetails(data.get(EntityConstants.Purchase.purchaseId) + "");
		fillDetailsData(purchaseData);
	}

	@Override
	protected String getDetailsPageName()
	{
		return "purchase_details";
	}
	
	@Override
	public void resetParentPage()
	{
		setSysInvoiceId(null);
		super.resetParentPage();
	}
}
