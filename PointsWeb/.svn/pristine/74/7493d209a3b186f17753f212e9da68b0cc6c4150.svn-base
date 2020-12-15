/**
 * 
 */
package com.ewhale.points.web.managedbean.agentseller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.AbsoluteBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.agent.proxy.AgentSellerServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;

/**
 * @author Ahmed Khalil
 */
@SessionScoped
@ManagedBean
public class AgentSellerHomeBean extends AbsoluteBean
{
	
	protected Logger LOG = Logger.getLogger(AgentSellerHomeBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Map<String, Object>> branchesList;

	@PostConstruct
	public void init()
	{
		// createMeterGaugeModels();
		loadBranches();
	}

	public Long getBranchId()
	{
		return (Long) FacesUtil.getLoginData().get(EntityConstants.Login.branchId);
	}

	public void setBranchId(Long branchId)
	{
		FacesUtil.getLoginData().put(EntityConstants.Login.branchId, branchId);
	}

	public Long getAgentId()
	{
		Long agentId = (Long) FacesUtil.getLoginData().get(EntityConstants.Login.agentId);
		LOG.debug("agent id .. " + agentId);
		return agentId;
	}

	public List<Map<String, Object>> getBranchesList()
	{
		return branchesList;
	}

	public void refresh()
	{
		LOG.debug("no thing to do here!! " + getBranchId());
	}

	public void loadBranches()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Branch.agentId, FacesUtil.getLoginAgentId());
		data.put(EntityConstants.Product.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
		AgentSellerServiceClient agentSellerServiceClient = ServiceClientUtil.getAgentSellerServiceClient(FacesUtil.getLoginToken());
		branchesList = agentSellerServiceClient.agentBranchesList(data);

	}
}
