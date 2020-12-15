/**
 * 
 */
package com.ewhale.points.web.managedbean.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.MeterGaugeChartModel;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.AbsoluteBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

/**
 * @author Ahmed Khalil
 */
@SessionScoped
@ManagedBean
public class UserHomeBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MeterGaugeChartModel pointsGaugeModel;

	private int points = 0;

	private List<Map<String, Object>> productsList;

	private List<Map<String, Object>> purchasesList;

	private List<Map<String, Object>> promotionsList;

	private List<ColumnModel> purchaseColumns = new ArrayList<>();

	@PostConstruct
	public void init()
	{
		refreshPageData();
	}

	public String refreshPageData()
	{

		try
		{
			createMeterGaugeModels();
			loadProducts();
			loadPurchases();
			loadPromotions();
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
		return "/pages/user/index.xhtml";
	}

	public MeterGaugeChartModel getPointsGaugeModel()
	{
		return pointsGaugeModel;
	}

	private MeterGaugeChartModel initPointsGaugeModel()
	{
		List<Number> intervals = new ArrayList<Number>();
		UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
		points = userServiceClient.getSumProfilePoints("" + FacesUtil.getLoginId());

		int max = (int) (points + (points * 1.5));
		intervals.add(max);
		return new MeterGaugeChartModel(points, intervals);
	}

	private void createMeterGaugeModels()
	{
		pointsGaugeModel = initPointsGaugeModel();
		pointsGaugeModel.setTitle("Points");
		// 66cc66,93b75f,E7E658,
		pointsGaugeModel.setSeriesColors("e7e718");
		// pointsGaugeModel.setGaugeLabel("km/h");
		pointsGaugeModel.setGaugeLabelPosition("bottom");
		pointsGaugeModel.setShowTickLabels(true);
		pointsGaugeModel.setLabelHeightAdjust(10);
		pointsGaugeModel.setIntervalOuterRadius(125);
	}

	public List<Map<String, Object>> getProductsList()
	{
		return productsList;
	}

	public void loadProducts()
	{
		try
		{
			Map<String, Object> data = new HashMap<>();
			data.put(EntityConstants.Product.pointsValue_Max, points);
			data.put(EntityConstants.Product.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
			UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
			productsList = userServiceClient.agentProductsList(data);
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}

	}

	public List<Map<String, Object>> getPurchasesList()
	{
		return purchasesList;
	}

	public void loadPurchases()
	{
		try
		{
			String[][] columnKeys = new String[][]
				{
						{ EntityConstants.Purchase.purchaseId, null },
						{ EntityConstants.Purchase.insertDate, null },
						{ EntityConstants.Purchase.agentInvoiceNumber, null },
						{ EntityConstants.Purchase.agentInvoiceValue, null },
						{ EntityConstants.Purchase.pointsValue, null },
						{ EntityConstants.Purchase.branch, EntityConstants.Branch.branchName },
						{ EntityConstants.Purchase.agent, EntityConstants.Agent.tradeMark } };

			Map<String, Object> data = new HashMap<>();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -3);
			data.put(EntityConstants.Purchase.insertDate_From_Search, calendar.getTime());
			data.put(EntityConstants.Purchase.profileId, FacesUtil.getLoginId());
			UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
			purchasesList = userServiceClient.userPurchasesList(data);
			populatePurchaseColumns(columnKeys);
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
	}

	public List<ColumnModel> getPurchaseColumns()
	{
		return purchaseColumns;
	}

	private void populatePurchaseColumns(String[][] columnKeys)
	{
		purchaseColumns.clear();
		for (String[] columnKey : columnKeys)
		{
			purchaseColumns.add(new ColumnModel(columnKey[0], columnKey[1], null));
			logger.debug(columnKey.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void viewDetails(Map<String, Object> data, String selectedField)
	{
		if (selectedField.equals("purchase"))
		{
			UserPurchaseBean userPurchaseBean = FacesUtil.getObjectFromSession(UserPurchaseBean.class, true);
			userPurchaseBean.viewDetails(data);
		}
		else if (selectedField.equals(EntityConstants.Agent.tradeMark))
		{
			UserAgentBean userAgentBean = FacesUtil.getObjectFromSession(UserAgentBean.class, true);
			userAgentBean.viewDetails((Map<String, Object>) data.get(EntityConstants.Purchase.agent));
		}
		else if (selectedField.equals(EntityConstants.Branch.branchName))
		{
			UserAgentBranchBean userAgentBranchBean = FacesUtil.getObjectFromSession(UserAgentBranchBean.class, true);
			userAgentBranchBean.viewDetails((Map<String, Object>) data.get(EntityConstants.Purchase.branch));
		}
	}

	public List<Map<String, Object>> getPromotionsList()
	{
		return promotionsList;
	}

	public void loadPromotions()
	{
		try
		{
			Map<String, Object> data = new HashMap<>();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			data.put(EntityConstants.Promotion.promotionDate_From_Search, calendar.getTime());
			data.put(EntityConstants.Promotion.countryId, FacesUtil.getLoginData().get(EntityConstants.Login.countryId));
			UserServiceClient userServiceClient = ServiceClientUtil.getUserServiceClient(FacesUtil.getLoginToken());
			promotionsList = userServiceClient.agentPromotionsList(data);
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}

	}
}
