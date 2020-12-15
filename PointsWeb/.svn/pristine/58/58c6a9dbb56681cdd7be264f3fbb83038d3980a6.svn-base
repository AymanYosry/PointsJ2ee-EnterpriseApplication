package com.ewhale.points.web.managedbean.agentadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.PaymentBean;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentPaymentBean extends PaymentBean
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date paymentDateFromSearch;

	private Date paymentDateToSearch;

	AgentAdminServiceClient agentAdminServiceClient = null;

	public Date getPaymentDateFromSearch()
	{
		return paymentDateFromSearch;
	}

	public void setPaymentDateFromSearch(Date paymentDateFromSearch)
	{
		this.paymentDateFromSearch = paymentDateFromSearch;
	}

	public Date getPaymentDateToSearch()
	{
		return paymentDateToSearch;
	}

	public void setPaymentDateToSearch(Date paymentDateToSearch)
	{
		this.paymentDateToSearch = paymentDateToSearch;
	}

	public void getPayments()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.SysPayment.agentId, FacesUtil.getLoginAgentId());
		data.put(EntityConstants.SysPayment.paymentDate_From_Search, paymentDateFromSearch);
		data.put(EntityConstants.SysPayment.paymentDate_To_Search, paymentDateToSearch);

		agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> agentPaymentsList = agentAdminServiceClient.agentPaymentsList(data);
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.SysPayment.sysPaymentId, null },
					{ EntityConstants.SysPayment.paymentDate, null },
					{ EntityConstants.SysPayment.paymentValue, null },
					{ EntityConstants.SysPayment.currency, EntityConstants.Currency.currencyName },
					{ EntityConstants.SysPayment.paymentMethod, EntityConstants.PaymentMethod.paymentMethodName },
					{ EntityConstants.SysPayment.insertDate, null } };
		populateTable(agentPaymentsList, columnKeys);
	}

	@Override
	protected void handlePostConstruct()
	{
		setHasMoreDetails(false);
		setCanUpdate(false);
		setCanAdd(false);
	}
}
