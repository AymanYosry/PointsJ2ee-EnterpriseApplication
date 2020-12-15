package com.ewhale.points.controller.facade;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ewhale.points.controller.facade.FunctionTypeFacade;
import com.ewhale.points.model.qualifiers.EntityClass;
import com.ewhale.points.model.qualifiers.EntityClassEnum;

/**
 * Session Bean implementation class FunctionTypeFacadeBean
 */
@Stateless
@Local(FunctionTypeFacade.class)
@LocalBean
public class FunctionTypeFacadeBean extends AbsoluteFacadeBean implements FunctionTypeFacade
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Inject
	public void setEntityClass(@EntityClass(EntityClassEnum.FUNCTIONTYPE) Class entityClass)
	{
		this.entityClass = entityClass;
	}

}
