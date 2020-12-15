/**
 * 
 */
package com.ewhale.points.ws.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.AgentFacade;
import com.ewhale.points.controller.facade.BranchFacade;
import com.ewhale.points.controller.facade.PointFacade;
import com.ewhale.points.controller.facade.PointsExchangeFacade;
import com.ewhale.points.controller.facade.ProductFacade;
import com.ewhale.points.controller.facade.PromotionFacade;
import com.ewhale.points.controller.facade.PurchaseFacade;

/**
 * @author Ahmad Khalil
 */
public class SynchronizationControler extends ServiceControler
{
	public Integer[] isSynchronizationNeeded(long dateInTimeMillis, long profileId, AgentFacade agentFacade, ProductFacade productFacade,
			PromotionFacade promotionFacade, BranchFacade branchFacade, PurchaseFacade purchaseFacade, PointsExchangeFacade pointsExchangeFacade)
					throws FacadeException
	{
		ArrayList<Integer> dataToSynchronize = isSynchronizationNeeded(dateInTimeMillis, profileId, agentFacade, productFacade, promotionFacade,
				branchFacade);
		if (purchaseFacade.isSynchronizationNeeded(dateInTimeMillis, profileId))
		{
			dataToSynchronize.add(EntityConstants.Synchronization.purchasesSyncNeeded);
		}
		if (pointsExchangeFacade.isSynchronizationNeeded(dateInTimeMillis, profileId))
		{
			dataToSynchronize.add(EntityConstants.Synchronization.pointsExchangeSyncNeeded);
		}
		return dataToSynchronize.toArray(new Integer[0]);
	}

	public ArrayList<Integer> isSynchronizationNeeded(long dateInTimeMillis, long profileId, AgentFacade agentFacade, ProductFacade productFacade,
			PromotionFacade promotionFacade, BranchFacade branchFacade) throws FacadeException
	{
		ArrayList<Integer> dataToSynchronize = new ArrayList<>();
		boolean agentSyncNeeded = agentFacade.isSynchronizationNeeded(dateInTimeMillis);
		boolean branchSyncNeeded = branchFacade.isSynchronizationNeeded(dateInTimeMillis);
		if (agentSyncNeeded)
		{
			dataToSynchronize.add(EntityConstants.Synchronization.agentSyncNeeded);
			dataToSynchronize.add(EntityConstants.Synchronization.categorySyncNeeded);
		}
		if (branchSyncNeeded)
		{
			dataToSynchronize.add(EntityConstants.Synchronization.branchSyncNeeded);
		}
		if (agentSyncNeeded || branchSyncNeeded)
		{
			dataToSynchronize.add(EntityConstants.Synchronization.countrySyncNeeded);
			dataToSynchronize.add(EntityConstants.Synchronization.districtSyncNeeded);
			dataToSynchronize.add(EntityConstants.Synchronization.stateSyncNeeded);
		}
		if (productFacade.isSynchronizationNeeded(dateInTimeMillis))
		{
			dataToSynchronize.add(EntityConstants.Synchronization.productSyncNeeded);
		}
		if (promotionFacade.isSynchronizationNeeded(dateInTimeMillis))
		{
			dataToSynchronize.add(EntityConstants.Synchronization.promotionSyncNeeded);
		}
		return dataToSynchronize;
	}

	public List<Map<String, Object>> userPurchasesList(PurchaseFacade facade, long profileId, long purchaseId) throws FacadeException
	{
		Map<String, Object> purchaseData = new HashMap<>();
		purchaseData.put(EntityConstants.Purchase.profileId, profileId);
		purchaseData.put(EntityConstants.Purchase.lastPurchaseId, purchaseId);
		List<Map<String, Object>> purchaseList = getList(facade, purchaseData);
		return purchaseList;
	}

	public List<Map<String, Object>> userPointsExchangeList(PointsExchangeFacade facade, long profileId, long pointsExchangeId) throws FacadeException
	{
		Map<String, Object> pointsExchangeData = new HashMap<>();
		pointsExchangeData.put(EntityConstants.PointsExchange.profileId, profileId);
		pointsExchangeData.put(EntityConstants.PointsExchange.lastPointsExchangeId, pointsExchangeId);
		List<Map<String, Object>> pointsExchangeList = getList(facade, pointsExchangeData);
		return pointsExchangeList;
	}

	public List<Map<String, Object>> userGainedPointsList(PointFacade facade, long id, Map<String, Object> pointsData) throws FacadeException
	{
		pointsData.put(EntityConstants.Point.txnType, 1);
		pointsData.put(EntityConstants.Point.profileId, id);
		List<Map<String, Object>> pointsList = getList(facade, pointsData);
		return pointsList;
	}

	public List<Map<String, Object>> userReleasedPointsList(PointFacade facade, long id, Map<String, Object> pointsData) throws FacadeException
	{
		pointsData.put(EntityConstants.Point.txnType, -1);
		pointsData.put(EntityConstants.Point.profileId, id);
		List<Map<String, Object>> pointsList = getList(facade, pointsData);
		return pointsList;
	}

	public void addSynchronizationNeededToList(List<Map<String, Object>> purchasesList, String lastSyncDateInTimeMillisStr, long profileId,
			AgentFacade agentFacade, ProductFacade productFacade, PromotionFacade promotionFacade, BranchFacade branchFacade) throws FacadeException
	{
		long dateInTimeMillis = Long.parseLong(lastSyncDateInTimeMillisStr);
		ArrayList<Integer> dataToSynchronizeArr = isSynchronizationNeeded(dateInTimeMillis, profileId, agentFacade, productFacade, promotionFacade,
				branchFacade);
		Integer[] dataToSynchronize = dataToSynchronizeArr.toArray(new Integer[0]);
		HashMap<String, Object> dataToSync = new HashMap<>();
		dataToSync.put(EntityConstants.Synchronization.dataToSync, dataToSynchronize);
		purchasesList.add(0, dataToSync);
	}
}
