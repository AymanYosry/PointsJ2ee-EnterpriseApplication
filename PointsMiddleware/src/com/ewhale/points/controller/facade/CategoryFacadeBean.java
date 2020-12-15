package com.ewhale.points.controller.facade;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ewhale.points.controller.facade.CategoryFacade;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class CategoryFacadeBean
 */
@Stateless
@Local(CategoryFacade.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CategoryFacadeBean extends AbsoluteFacadeBean implements CategoryFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.CATEGORY) Class entityClass)
	{
		this.entityClass = entityClass;
	}
}
