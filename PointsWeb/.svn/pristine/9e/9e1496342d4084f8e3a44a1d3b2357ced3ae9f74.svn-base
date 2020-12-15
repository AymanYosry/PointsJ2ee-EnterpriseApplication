package com.ewhale.points.web.managedbean.main;

import java.util.Map;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.ewhale.points.common.util.EntityConstants;

public class BranchBean extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long branchId;

	private String address;

	private String branchName;

	private Double locationLatitude;

	private Double locationLongitude;

	private String tel;

	private Long agentId;

	private String agentTradeMark;

	private Integer stateId;

	private Short countryId;

	private String countryName;

	private String stateName;

	private MapModel branchMapModel;

	public Long getBranchId()
	{
		return branchId;
	}

	public void setBranchId(Long branchId)
	{
		this.branchId = branchId;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getBranchName()
	{
		return branchName;
	}

	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	public Double getLocationLatitude()
	{
		return locationLatitude;
	}

	public void setLocationLatitude(Double locationLatitude)
	{
		this.locationLatitude = locationLatitude;
	}

	public Double getLocationLongitude()
	{
		return locationLongitude;
	}

	public void setLocationLongitude(Double locationLongitude)
	{
		this.locationLongitude = locationLongitude;
	}

	public MapModel getBranchMapModel()
	{
		return branchMapModel;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public Long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(Long agentId)
	{
		this.agentId = agentId;
	}

	public String getAgentTradeMark()
	{
		return agentTradeMark;
	}

	public void setAgentTradeMark(String agentTradeMark)
	{
		this.agentTradeMark = agentTradeMark;
	}

	public Integer getStateId()
	{
		return stateId;
	}

	public void setStateId(Integer stateId)
	{
		this.stateId = stateId;
	}

	public Short getCountryId()
	{
		return countryId;
	}

	public void setCountryId(Short countryId)
	{
		this.countryId = countryId;
	}

	public String getCountryName()
	{
		return countryName;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	@SuppressWarnings("unchecked")
	protected void fillDetailsData(Map<String, Object> branchData)
	{
		address = (String) branchData.get(EntityConstants.Branch.address);
		branchName = (String) branchData.get(EntityConstants.Branch.branchName);
		locationLatitude = ((Number) branchData.get(EntityConstants.Branch.locationLatitude)).doubleValue();
		locationLongitude = ((Number) branchData.get(EntityConstants.Branch.locationLongitude)).doubleValue();
		tel = (String) branchData.get(EntityConstants.Branch.tel);
		agentId = ((Number) branchData.get(EntityConstants.Branch.agentId)).longValue();
		stateId = ((Number) branchData.get(EntityConstants.Branch.stateId)).intValue();
		countryId = ((Number) branchData.get(EntityConstants.Branch.countryId)).shortValue();
		countryName = ((String) ((Map<String, Object>) branchData.get(EntityConstants.Branch.country)).get(EntityConstants.Country.countryName));
		stateName = ((String) ((Map<String, Object>) branchData.get(EntityConstants.Branch.state)).get(EntityConstants.State.stateName));
		agentTradeMark = ((String) ((Map<String, Object>) branchData.get(EntityConstants.Branch.agent)).get(EntityConstants.Agent.tradeMark));
		
		branchMapModel = new DefaultMapModel();
		LatLng location = new LatLng(locationLatitude, locationLongitude);
		Marker branchMarker = new Marker(location);
		branchMapModel.addOverlay(branchMarker);
	}
	
}
