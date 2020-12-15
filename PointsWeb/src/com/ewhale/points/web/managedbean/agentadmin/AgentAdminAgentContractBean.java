package com.ewhale.points.web.managedbean.agentadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.ContractBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

@SessionScoped
@ManagedBean
public class AgentAdminAgentContractBean extends ContractBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void handlePostConstruct()
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Contract.agentId, FacesUtil.getLoginAgentId());
		Map<String, Object> contractData = ((List<Map<String, Object>>) agentAdminServiceClient.getAgentContractsList(data)).get(0);
		fillDetailsData(contractData);
	}

	public void updateAgentContract()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Contract.contractId, getContractId());
		data.put(EntityConstants.Contract.discountPercent, getDiscountPercent());
		data.put(EntityConstants.Contract.updateEmpId, FacesUtil.getLoginId());
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		agentAdminServiceClient.updateAgentContract(data);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		AgentAdminServiceClient agentAdminServiceClient = ServiceClientUtil.getAgentAdminServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> contractData = agentAdminServiceClient.contractDetails(data.get(EntityConstants.Contract.contractId) + "");
		fillDetailsData(contractData);
	}
}
