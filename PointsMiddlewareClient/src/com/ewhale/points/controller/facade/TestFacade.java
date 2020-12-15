package com.ewhale.points.controller.facade;

import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;

public interface TestFacade extends AbsoluteFacade
{

	void addData(Map<String, Object> data1, Map<String, Object> data2) throws FacadeException;


}
