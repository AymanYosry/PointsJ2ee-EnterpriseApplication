package com.ewhale.points.web.managedbean.user;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.PromotionBean;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

@SessionScoped
@ManagedBean
public class UserAgentPromotionBean extends PromotionBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String agentName;
	private List<Map<String, Object>> allAgentsList;

	public String getAgentName()
	{
		return agentName;
	}

	public List<Map<String, Object>> getAgentsList()
	{
		return allAgentsList;
	}
	public void getPromotions()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.Promotion.agentId, getAgentId());
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MONTH, -2);
		data.put(EntityConstants.Promotion.promotionDate_From_Search, today.getTime());
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> promotionsList = userServiceClient.agentPromotionsList(data);

		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.Promotion.promotionDate, null },
					{ EntityConstants.Promotion.agent, EntityConstants.Agent.tradeMark },
					{ EntityConstants.Promotion.message, null } };

		populateTable(promotionsList, columnKeys);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> promotionData = userServiceClient.agentPromotionDetails(data.get(EntityConstants.Promotion.promotionId) + "");
		fillDetailsData(promotionData);
		agentName = ((String) ((Map<String, Object>) promotionData.get(EntityConstants.Promotion.agent)).get(EntityConstants.Agent.tradeMark));
	}

	@Override
	protected String getDetailsPageName()
	{
		return "promotion_details.xhtml";
	}

	protected void handlePostConstruct()
	{
		setCanAdd(false);
		setCanUpdate(false);
		allAgentsList = UserBeanUtils.getAllAgentsList();
		getPromotions();
	}
}
