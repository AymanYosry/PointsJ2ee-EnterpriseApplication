package com.ewhale.points.web.managedbean.systemsales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.web.managedbean.main.AbsoluteBean;
import com.ewhale.points.web.managedbean.main.FacesUtil;
import com.ewhale.points.ws.main.proxy.ServiceClientUtil;
import com.ewhale.points.ws.system.proxy.SystemSalesServiceClient;

@SessionScoped
@ManagedBean
public class StateBean extends AbsoluteBean
{
	protected Logger LOG = Logger.getLogger(StateBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer stateId;

	private Byte countryId;

	private String stateName;

	private String countryName;

	private List<Map<String, Object>> countriesList;

	public StateBean()
	{
		setHasMoreDetails(false);
	}

	public Integer getStateId()
	{
		return stateId;
	}

	public void setStateId(Integer stateId)
	{
		this.stateId = stateId;
	}

	public Byte getCountryId()
	{
		return countryId;
	}

	public void setCountryId(Byte countryId)
	{
		this.countryId = countryId;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getCountryName()
	{
		return countryName;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}

	public List<Map<String, Object>> getCountriesList()
	{
		return countriesList;
	}

	public void setCountriesList(List<Map<String, Object>> countriesList)
	{
		this.countriesList = countriesList;
	}

	@Override
	protected String getDetailsPageName()
	{
		return null; // has no more details
	}

	@Override
	protected String getUpdatePageName()
	{
		return "state_details.xhtml";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void loadDetailsData(Map<String, Object> data)
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		Map<String, Object> state = systemSalesServiceClient.stateDetails(data.get(EntityConstants.State.stateId).toString());
		stateId = ((Number) state.get(EntityConstants.State.stateId)).intValue();
		stateName = (String) state.get(EntityConstants.State.stateName);
		countryName = (String) ((Map<String, Object>) state.get(EntityConstants.State.country)).get(EntityConstants.Country.countryName);
		countryId = ((Number) ((Map<String, Object>) state.get(EntityConstants.State.country)).get(EntityConstants.Country.countryId)).byteValue();
	}

	public void viewStatesList()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.State.countryId, countryId);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		List<Map<String, Object>> allStatesCollection = systemSalesServiceClient.getStatesList(data);
		String[][] columnKeys = new String[][]
			{
					{ EntityConstants.State.stateId, null },
					{ EntityConstants.State.stateName, null },
					{ EntityConstants.State.country, EntityConstants.Country.countryName } };
		String[] linkableLists = new String[]
			{ EntityConstants.State.districts };
		populateTable(allStatesCollection, linkableLists, columnKeys);
	}

	public void addState()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.State.countryId, countryId);
		LOG.debug("countryId = "+countryId);
		data.put(EntityConstants.State.stateName, stateName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.addState(data);
		viewStatesList();
		//IMP_Ahmed get Strings from aplication.properties file
		FacesUtil.growlInfoMessage("Success", "New record has been added successfully");

	}

	@Override
	public String viewListDetails(Map<String, Object> data, String selectedField)
	{
		if (selectedField.equals(EntityConstants.State.districts))
		{
			int selectedStateId = ((Number)data.get(EntityConstants.State.stateId)).intValue();
			DistrictBean districtBean = FacesUtil.getObjectFromSession(DistrictBean.class, true);
			districtBean.setStateId(selectedStateId);
			//IMP_Badawy you have to call the search method as if the button clicked 
			districtBean.viewDistrictsList();
			return "district_search.xhtml";
		}
		return null;
	}
	public void updateState()
	{
		Map<String, Object> data = new HashMap<>();
		data.put(EntityConstants.State.stateId, stateId);
		data.put(EntityConstants.State.countryId, countryId);
		data.put(EntityConstants.State.stateName, stateName);
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.updateState(data);

	}

	public void deleteState()
	{
		SystemSalesServiceClient systemSalesServiceClient = ServiceClientUtil.getSystemSalesServiceClient(FacesUtil.getLoginToken());
		systemSalesServiceClient.deleteState(stateId + "");

	}

	@Override
	protected void resetToAdd()
	{
		setStateName(null);
		setStateId(null);
		setCountryId(null);
	}
}
