/**
 * 
 */
package com.ewhale.points.common.exception;

import com.ewhale.points.common.util.ExceptionConstants;

/**
 * @author Ayman Yosry
 */
public class ApplicationException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode = ExceptionConstants.UNKNOWN_EXCETION_STATUS_CODE;

	/**
	 * @param statusCode
	 */
	protected ApplicationException(int statusCode)
	{
		super();
		this.statusCode = statusCode;
	}

	/**
	 * @param message
	 * @param statusCode
	 */
	protected ApplicationException(String message, int statusCode)
	{
		super(message);
		this.statusCode = statusCode;
	}

	/**
	 * @param cause
	 * @param statusCode
	 */
	protected ApplicationException(Throwable cause, int statusCode)
	{
		super(cause);
		this.statusCode = statusCode;
	}

	/**
	 * @param message
	 * @param cause
	 * @param statusCode
	 */
	protected ApplicationException(String message, Throwable cause, int statusCode)
	{
		super(message, cause);
		this.statusCode = statusCode;
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 * @param statusCode
	 */
	protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int statusCode)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		this.statusCode = statusCode;
	}

	public int getStatusCode()
	{
		return statusCode;
	}
}
