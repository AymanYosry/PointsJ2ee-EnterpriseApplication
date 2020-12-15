package com.ewhale.points.controller.facade;

import java.util.Map;

import com.ewhale.points.common.exception.FacadeException;
import com.ewhale.points.common.exception.ValidationException;

public interface AuthenticationFacade extends AbsoluteFacade
{
	Map<String, Object> login(Map<String, Object> loginData) throws FacadeException, ValidationException;

	void resetPassword(String mobile) throws FacadeException;

	Map<String, Object> validateUser(Map<String, Object> loginData) throws FacadeException, ValidationException;
	
	void logout(String token) throws FacadeException;
}