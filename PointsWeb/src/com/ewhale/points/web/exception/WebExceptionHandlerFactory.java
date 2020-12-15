/**
 * 
 */
package com.ewhale.points.web.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerFactory;

/**
 * @author Ayman Yosry
 */
public class WebExceptionHandlerFactory extends PrimeExceptionHandlerFactory
{

	/**
	 * @param wrapped
	 */
	public WebExceptionHandlerFactory(ExceptionHandlerFactory wrapped)
	{
		super(wrapped);
	}

	@Override
	public ExceptionHandler getExceptionHandler()
	{
		return new WebExceptionHandler(getWrapped().getExceptionHandler());
	}

}
