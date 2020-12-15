package com.ewhale.points.common.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ewhale.points.common.exception.ServiceException;
import com.ewhale.points.common.util.ExceptionConstants;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException>
{
	@Override
	public Response toResponse(ServiceException serviceException)
	{
		return Response.status(serviceException.getStatusCode()).entity(serviceException.getMessage()).type(ExceptionConstants.EXCEPTION_MESSAGE_TYPE).build();
	}
}
