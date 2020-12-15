package com.ewhale.points.controller.facade;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;
import com.ewhale.points.controller.facade.DistrictFacade;
import com.ewhale.points.model.entity.AbsoluteEntity;
import com.ewhale.points.model.entity.Country;
import com.ewhale.points.model.entity.District;
import com.ewhale.points.model.entity.State;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class DistrictFacadeBean
 */
@Stateless
@Local(DistrictFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DistrictFacadeBean extends AbsoluteFacadeBean implements DistrictFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.DISTRICT) Class entityClass)
	{
		this.entityClass = entityClass;
	}

	@Override
	protected void addEntityDetails(AbsoluteEntity entity, Map<String, Object> data) throws EntityException
	{
		byte countryId = ((Number) data.get(EntityConstants.District.countryId)).byteValue();
		Country country = em.viewRecordDetails(Country.class, countryId);
		int stateId = ((Number) data.get(EntityConstants.District.stateId)).intValue();
		State state = em.viewRecordDetails(State.class, stateId);
		// DistrictPK districtPK=(DistrictPK)getPrimaryKey(countryId,stateId,0);
		// int districtId = ((Number) data.get(EntityConstants.District.districtId)).intValue();
		// ((District) entity).setDistrictId(districtId);
		((District) entity).setState(state);
		((District) entity).setCountry(country);
	}

	@Override
	protected void updateEntityDetails(Map<String, Object> data) throws EntityException
	{
		data.remove(EntityConstants.District.countryId);
		data.remove(EntityConstants.District.stateId);
	}
	// @Override
	// protected Object getPrimaryKey(Number... id)
	// {
	// DistrictPK districtPK = new DistrictPK();
	// districtPK.setCountryId(id[0].byteValue());
	// districtPK.setStateId(id[1].intValue());
	// districtPK.setDistrictId(id[2].intValue());
	// return districtPK;
	// }

}
