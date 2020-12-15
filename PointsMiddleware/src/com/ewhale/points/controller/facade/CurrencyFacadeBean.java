package com.ewhale.points.controller.facade;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.controller.facade.CurrencyFacade;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class CurrencyFacadeBean
 */
@Stateless
@Local(CurrencyFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CurrencyFacadeBean extends AbsoluteFacadeBean implements CurrencyFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.CURRENCY) Class entityClass)
	{
		this.entityClass = entityClass;
	}
}
