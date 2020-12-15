package com.ewhale.points.web.managedbean.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.web.managedbean.main.PointsExchangeBean;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

@SessionScoped
@ManagedBean
public class UserExchangeBean extends PointsExchangeBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Map<String, Object>> allAgentsList;

	private Date pointsExchangeDate_from, pointsExchangeDate_to;

	public List<Map<String, Object>> getAllAgentsList()
	{
		return allAgentsList;
	}

	public Date getPointsExchangeDate_from()
	{
		return pointsExchangeDate_from;
	}

	public void setPointsExchangeDate_from(Date pointsExchangeDate_from)
	{
		this.pointsExchangeDate_from = pointsExchangeDate_from;
	}

	public Date getPointsExchangeDate_to()
	{
		return pointsExchangeDate_to;
	}

	public void setPointsExchangeDate_to(Date pointsExchangeDate_to)
	{
		this.pointsExchangeDate_to = pointsExchangeDate_to;
	}

	public void getUserExchangesList()
	{
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.PointsExchange.pointsExchangeId, null },
					{ EntityConstants.PointsExchange.insertDate, null },
					{ EntityConstants.PointsExchange.product, EntityConstants.Product.productName },
					{ EntityConstants.PointsExchange.pointsValue, null },
					{ EntityConstants.PointsExchange.agent, EntityConstants.Agent.tradeMark },
					{ EntityConstants.PointsExchange.branch, EntityConstants.Branch.branchName } };

		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.PointsExchange.agentId, getAgentId());
		data.put(EntityConstants.PointsExchange.profileId, FacesUtil.getLoginId());
		data.put(EntityConstants.PointsExchange.insertDate_From_Search, pointsExchangeDate_from);
		data.put(EntityConstants.PointsExchange.insertDate_To_Search, pointsExchangeDate_to);

		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> userExchangesList = userServiceClient.userPointsExchangeList(data);

		populateTable(userExchangesList, columnKeys);
	}

	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> exchangeDetails = userServiceClient
				.viewPointsExchangeDetails(data.get(EntityConstants.PointsExchange.pointsExchangeId) + "");
		fillDetailsData(exchangeDetails);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void viewDetails(Map<String, Object> data, String selectedField)
	{
		if (selectedField.equals(EntityConstants.Agent.tradeMark))
		{
			UserAgentBean userAgentBean = FacesUtil.getObjectFromSession(UserAgentBean.class, true);
			userAgentBean.viewDetails((Map<String, Object>) data.get(EntityConstants.PointsExchange.agent));
		}
		else if (selectedField.equals(EntityConstants.Branch.branchName))
		{
			UserAgentBranchBean userAgentBranchBean = FacesUtil.getObjectFromSession(UserAgentBranchBean.class, true);
			userAgentBranchBean.viewDetails((Map<String, Object>) data.get(EntityConstants.PointsExchange.branch));
		}
		else if (selectedField.equals(EntityConstants.Product.productName))
		{
			UserAgentProductBean userAgentProductBean = FacesUtil.getObjectFromSession(UserAgentProductBean.class, true);
			userAgentProductBean.viewDetails((Map<String, Object>) data.get(EntityConstants.PointsExchange.product));
		}
	}

	@Override
	protected String getDetailsPageName()
	{
		return "exchange_details";
	}

	@Override
	protected void handlePostConstruct()
	{
		setCanAdd(false);
		setCanUpdate(false);
		setHasMoreDetails(true);
		allAgentsList = UserBeanUtils.getAllAgentsList();
	}

	@Override
	public void resetParentPage()
	{
		super.resetParentPage();
		setAgentId(null);
	}

	// IMP_Ahmed should be removed -- only done from the mobile
	public void confirm()
	{
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		userServiceClient.confirmPointsId("" + getPointsExchangeId(), "" + FacesUtil.getLoginId());
		getUserExchangesList();
		closeDialoge();
	}

	public void reject()
	{
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		userServiceClient.rejectPoints("" + getPointsExchangeId(), "" + FacesUtil.getLoginId());
		getUserExchangesList();
		closeDialoge();
	}
}
