package com.ewhale.points.common.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface AbortResponses
{
	Response UNAUTHORIZED_RESPONSE = Response.status(Response.Status.UNAUTHORIZED).type(MediaType.TEXT_PLAIN_TYPE).entity("Invalid Token.").build();
}
