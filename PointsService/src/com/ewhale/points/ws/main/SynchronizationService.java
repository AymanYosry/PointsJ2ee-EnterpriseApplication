/**
 * 
 */
package com.ewhale.points.ws.main;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.ewhale.points.common.annotations.VerifyToken;
import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ServiceException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.common.util.ExceptionConstants;
import com.ewhale.points.controller.facade.AgentFacade;
import com.ewhale.points.controller.facade.BranchFacade;
import com.ewhale.points.controller.facade.CategoryFacade;
import com.ewhale.points.controller.facade.CountryFacade;
import com.ewhale.points.controller.facade.DistrictFacade;
import com.ewhale.points.controller.facade.PointFacade;
import com.ewhale.points.controller.facade.PointsExchangeFacade;
import com.ewhale.points.controller.facade.ProductFacade;
import com.ewhale.points.controller.facade.PromotionFacade;
import com.ewhale.points.controller.facade.PurchaseFacade;
import com.ewhale.points.controller.facade.StateFacade;

/**
 * @author Ayman Yosry
 */

@Path("/Synchronization")
@VerifyToken
@Produces(ServiceHeading.MEDIA_TYPE)
public class SynchronizationService implements ServiceHeading
{
	private SynchronizationControler controler = new SynchronizationControler();

	protected Logger LOG = Logger.getLogger(SynchronizationService.class);

	@EJB
	private CountryFacade countryFacade;

	@EJB
	private StateFacade stateFacade;

	@EJB
	private DistrictFacade districtFacade;

	@EJB
	private CategoryFacade categoryFacade;

	@EJB
	private AgentFacade agentFacade;

	@EJB
	private BranchFacade branchFacade;

	@EJB
	private ProductFacade productFacade;

	@EJB
	private PromotionFacade promotionFacade;

	@EJB
	private PurchaseFacade purchaseFacade;

	@EJB
	private PointsExchangeFacade pointsExchangeFacade;

	@EJB
	private PointFacade pointFacade;

	@POST
	@Path("/countries")
	public List<Map<String, Object>> getAllCountries()
	{
		try
		{
			List<Map<String, Object>> allCountries = controler.getAll(countryFacade);
			return allCountries;
		}
		catch (FacadeException e)
		{
			// e.printStackTrace();
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/states")
	public List<Map<String, Object>> getAllStates(Map<String, Object> stateData)
	{
		try
		{
			List<Map<String, Object>> allStates = controler.getList(stateFacade, stateData);
			return allStates;
		}
		catch (FacadeException e)
		{
			// e.printStackTrace();
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/districts")
	public List<Map<String, Object>> getAllDistricts(Map<String, Object> districtData)
	{
		try
		{
			List<Map<String, Object>> alldistricts = controler.getList(districtFacade, districtData);
			return alldistricts;
		}
		catch (FacadeException e)
		{
			// e.printStackTrace();
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/categories")
	public List<Map<String, Object>> getAllCategories()
	{
		try
		{
			List<Map<String, Object>> allCategories = controler.getAll(categoryFacade);
			return allCategories;
		}
		catch (FacadeException e)
		{
			// e.printStackTrace();
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/agentsList")
	public List<Map<String, Object>> getAgentsList(Map<String, Object> data)
	{
		try
		{
			data.put(EntityConstants.Agent.statusId, EntityConstants.Status.Fixed.activeStatus.ID);
			// IMP_Ayman this should be removed if there is a timer that activate the agent or change it to pending
			long today = new Date().getTime();
			data.put(EntityConstants.Agent.contractEndDate_From_Search, today);
			data.put(EntityConstants.Agent.contractStartDate_To_Search, today);
			List<Map<String, Object>> agentsList = controler.getList(agentFacade, data);
			return agentsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/branchesList")
	public List<Map<String, Object>> getBranchesList(Map<String, Object> data)
	{
		try
		{
			// IMP_Ahmed this should return all the branches of activated agents only
			// data.put(EntityConstants.Branch.statusId, EntityConstants.Status.Fixed.activeStatus.ID);
			List<Map<String, Object>> branchesList = controler.getList(branchFacade, data);
			return branchesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/productsList")
	public List<Map<String, Object>> getProductsList(Map<String, Object> data)
	{
		try
		{
			data.put(EntityConstants.Product.statusId, EntityConstants.Status.Fixed.activeStatus.ID);
			List<Map<String, Object>> branchesList = controler.getList(productFacade, data);
			return branchesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/promotionsList")
	public List<Map<String, Object>> getPromotionsList(Map<String, Object> data)
	{
		try
		{
			data.put(EntityConstants.Promotion.statusId, EntityConstants.Status.Fixed.activeStatus.ID);
			List<Map<String, Object>> branchesList = controler.getList(promotionFacade, data);
			return branchesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@Path("/synchronizationNeeded/{date}/{profileId}")
	public Integer[] isSynchronizationNeeded(@PathParam("date") String dateInTimeMillisStr, @PathParam("profileId") String profileIdStr)
	{
		try
		{
			long dateInTimeMillis = Long.parseLong(dateInTimeMillisStr);
			long profileId = Long.parseLong(profileIdStr);
			return controler.isSynchronizationNeeded(dateInTimeMillis, profileId, agentFacade, productFacade, promotionFacade, branchFacade,
					purchaseFacade, pointsExchangeFacade);
		}
		catch (NumberFormatException e)
		{
			LOG.debug("Problem While Validation ", e);
			throw new ServiceException(ExceptionConstants.INVALID_DATA_EX_MSG, ExceptionConstants.VALIDATION_EXCETION_STATUS_CODE);
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	/**
	 * @param lastSyncDateInTimeMillisStr
	 * @param profileId
	 * @param lastPurchaseId the last id of purchase in last synchronisation -1 if install
	 * @return
	 */
	@POST
	@VerifyToken
	@Path("/userPurchasesList/{lastSyncDate}/{profileId}/{lastPurchaseId}")
	public List<Map<String, Object>> userPurchasesList(@PathParam("lastSyncDate") String lastSyncDateInTimeMillisStr,
			@PathParam("profileId") String profileId, @PathParam("lastPurchaseId") String lastPurchaseId)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			Long purchaseId = Long.valueOf(lastPurchaseId);
			List<Map<String, Object>> purchasesList = controler.userPurchasesList(purchaseFacade, id, purchaseId);
			controler.addSynchronizationNeededToList(purchasesList, lastSyncDateInTimeMillisStr, id, agentFacade, productFacade, promotionFacade,
					branchFacade);
			return purchasesList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	/**
	 * @param lastSyncDateInTimeMillisStr
	 * @param profileId
	 * @param lastPointsExchangeId the last id of points exchange in last synchronisation -1 if install
	 * @return
	 */
	@POST
	@VerifyToken
	@Path("/userPointsExchangeList/{lastSyncDate}/{profileId}/{lastPointsExchangeId}")
	public List<Map<String, Object>> userPointsExchangeList(@PathParam("lastSyncDate") String lastSyncDateInTimeMillisStr,
			@PathParam("profileId") String profileId, @PathParam("lastPointsExchangeId") String lastPointsExchangeId)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			Long pointsExchangeId = Long.valueOf(lastPointsExchangeId);
			List<Map<String, Object>> pointsExchangeList = controler.userPointsExchangeList(pointsExchangeFacade, id, pointsExchangeId);
			controler.addSynchronizationNeededToList(pointsExchangeList, lastSyncDateInTimeMillisStr, id, agentFacade, productFacade, promotionFacade,
					branchFacade);
			return pointsExchangeList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/userGainedPointsList/{profileId}")
	public List<Map<String, Object>> userGainedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			List<Map<String, Object>> pointsList = controler.userGainedPointsList(pointFacade, id, pointsData);
			return pointsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

	@POST
	@VerifyToken
	@Path("/userReleasedPointsList/{profileId}")
	public List<Map<String, Object>> userReleasedPointsList(@PathParam("profileId") String profileId, Map<String, Object> pointsData)
	{
		try
		{
			Long id = Long.valueOf(profileId);
			List<Map<String, Object>> pointsList = controler.userReleasedPointsList(pointFacade, id, pointsData);
			return pointsList;
		}
		catch (FacadeException e)
		{
			LOG.error(e.getMessage(), e);
			throw new ServiceException("Problem While retrieving data", e.getStatusCode());
		}
	}

}
