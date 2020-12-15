package com.ewhale.points.common.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClassCastExceptionMapper implements ExceptionMapper<ClassCastException>
{

	@Override
	public Response toResponse(ClassCastException serviceException)
	{
		return Response.status(532).entity(serviceException.getMessage()).type("text/plain").build();
	}

}
