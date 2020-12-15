package com.ewhale.points.web.managedbean.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.AgentBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.LookUpServiceClient;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

@SessionScoped
@ManagedBean
public class UserAgentBean extends AgentBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Map<String, Object>> categoriesList;

	private List<Map<String, Object>> statesList;

	private Integer stateId;

	public List<Map<String, Object>> getCategoriesList()
	{
		return categoriesList;
	}

	public void setCategoriesList(List<Map<String, Object>> categoriesList)
	{
		this.categoriesList = categoriesList;
	}

	public List<Map<String, Object>> getStatesList()
	{
		return statesList;
	}

	public void setStatesList(List<Map<String, Object>> statesList)
	{
		this.statesList = statesList;
	}

	public Integer getStateId()
	{
		return stateId;
	}

	public void setStateId(Integer stateId)
	{
		this.stateId = stateId;
	}

	@Override
	protected void handlePostConstruct()
	{
		loadStatesList();
		loadCategoriesList();
		setCanAdd(false);
		setCanUpdate(false);
	}

	public void agentsList()
	{
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Agent.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
		data.put(EntityConstants.Agent.stateId, stateId);
		data.put(EntityConstants.Agent.categoryIds, getCategoryIds());
		List<Map<String, Object>> agentsList = userServiceClient.agentsList(data);
		String[] linkable = new String[]
			{ EntityConstants.Agent.branches, EntityConstants.Agent.products, EntityConstants.Agent.promotions, EntityConstants.Agent.purchases,
					EntityConstants.Agent.pointsExchanges };

		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Agent.tradeMark, null },
					{ EntityConstants.Agent.callCenter, null } };
		populateTable(agentsList, linkable, columnKeys);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> agentData = userServiceClient.agentDetails(data.get(EntityConstants.Agent.agentId) + "");
		fillDetailsData(agentData);

	}

	@Override
	protected String getDetailsPageName()
	{
		return "agent_details";
	}

	@Override
	public String viewListDetails(Map<String, Object> data, String selectedField)
	{
		logger.debug("-----------" + selectedField);
		long selectedAgentId = ((Number) data.get(EntityConstants.Agent.agentId)).longValue();
//		String selectedAgentTradeMark = (String) data.get(EntityConstants.Agent.tradeMark);
		if (selectedField.equals(EntityConstants.Agent.branches))
		{
			UserAgentBranchBean userAgentBranchBean = FacesUtil.getObjectFromSession(UserAgentBranchBean.class, true);
			userAgentBranchBean.setAgentId(selectedAgentId);
//			userAgentBranchBean.setParentPageTitle(selectedAgentTradeMark);
//			userAgentBranchBean.setParentPage("agent_search");
			userAgentBranchBean.getAgentBranchesList();
			return "branch_search";
		}
		else if (selectedField.equals(EntityConstants.Agent.products))
		{
			UserAgentProductBean userAgentProductBean = FacesUtil.getObjectFromSession(UserAgentProductBean.class, true);
			userAgentProductBean.setAgentId(selectedAgentId);
//			userAgentProductBean.setParentPageTitle(selectedAgentTradeMark);
//			userAgentProductBean.setParentPage("agent_search");
			userAgentProductBean.getProducts();
			return "product_search";
		}
		else if (selectedField.equals(EntityConstants.Agent.promotions))
		{
			UserAgentPromotionBean userAgentPromotionBean = FacesUtil.getObjectFromSession(UserAgentPromotionBean.class, true);
			userAgentPromotionBean.setAgentId(selectedAgentId);
//			userAgentPromotionBean.setParentPageTitle(selectedAgentTradeMark);
//			userAgentPromotionBean.setParentPage("agent_search");
			userAgentPromotionBean.getPromotions();
			return "promotion_search";
		}
		else if (selectedField.equals(EntityConstants.Agent.purchases))
		{
			UserPurchaseBean userPurchaseBean = FacesUtil.getObjectFromSession(UserPurchaseBean.class, true);
			userPurchaseBean.setAgentId(selectedAgentId);
//			userPurchaseBean.setParentPageTitle(selectedAgentTradeMark);
//			userPurchaseBean.setParentPage("agent_search");
			userPurchaseBean.getUserPurchasesList();
			return "purchase_search";
		}
		else if (selectedField.equals(EntityConstants.Agent.pointsExchanges))
		{
			UserExchangeBean userExchangeBean = FacesUtil.getObjectFromSession(UserExchangeBean.class, true);
			userExchangeBean.setAgentId(selectedAgentId);
//			userExchangeBean.setParentPageTitle(selectedAgentTradeMark);
//			userExchangeBean.setParentPage("agent_search");
			userExchangeBean.getUserExchangesList();
			return "exchange_search";
		}
		return null;
	}

	public void loadStatesList()
	{
		LookUpServiceClient lookUpServiceClient = ServiceClientUtil.getLookUpServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.State.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
		statesList = lookUpServiceClient.getAllStates(data);
	}

	public void loadCategoriesList()
	{
		LookUpServiceClient lookUpServiceClient = ServiceClientUtil.getLookUpServiceClient(FacesUtil.getLoginToken());
		categoriesList = lookUpServiceClient.getAllCategories();
	}

}
