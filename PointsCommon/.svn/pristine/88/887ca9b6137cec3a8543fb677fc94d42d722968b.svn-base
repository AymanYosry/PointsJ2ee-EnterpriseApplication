package com.ewhale.points.common.exception;

import javax.ws.rs.WebApplicationException;

public class ServiceException extends WebApplicationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;

	public ServiceException(String message, int statusCode)
	{
		super(message, statusCode);
		this.statusCode = statusCode;
	}

	public int getStatusCode()
	{
		return statusCode;
	}
}
