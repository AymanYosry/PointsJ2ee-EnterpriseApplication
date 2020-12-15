/**
 * 
 */
package com.ewhale.points.ws.interceptors;

import javax.annotation.Priority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

import org.jboss.logging.Logger;

import com.ewhale.points.common.util.AbortResponses;
import com.ewhale.points.common.util.AppConstants;

/**
 * @author Ayman Yosry
 */

@Priority(value = 10)
public class ServiceClientTokenVerificationInterceptor implements ClientRequestFilter, ClientResponseFilter
{
	protected Logger LOG= Logger.getLogger(ServiceClientTokenVerificationInterceptor.class);

	private String token;

	public ServiceClientTokenVerificationInterceptor(String token)
	{
		LOG.debug("ServiceClientTokenVerificationInterceptor Instantiation with a token " + token);
		this.token = token;
	}

	/**
	 * filter pre request (before calling the server)
	 */
	@Override
	public void filter(ClientRequestContext requestContext)
	{
		LOG.debug("################ in ServiceClientTokenVerificationInterceptor");
		if (token == null)
			requestContext.abortWith(AbortResponses.UNAUTHORIZED_RESPONSE);
		requestContext.getHeaders().add(AppConstants.SecurityConstants.TOKEN, token);

		LOG.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%% INTINTINT " + this.getClass().getSimpleName() + " ##########"
				+token+ "################");
	}

	/**
	 * filter post request (after calling the server)
	 */
	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
	{
		LOG.debug("########################## " + this.getClass().getSimpleName() + " ##########################");
		String requestToken = (String) requestContext.getHeaders().getFirst(AppConstants.SecurityConstants.TOKEN);
		String SIGNATURE = (String) responseContext.getHeaders().getFirst(AppConstants.SecurityConstants.SIGNATURE);
		String responseToken = (String) responseContext.getHeaders().getFirst(AppConstants.SecurityConstants.TOKEN);
		LOG.debug("----------- response token :" + responseToken);
		LOG.debug("----------- request Token :" + requestToken);
		if (responseToken==null||!responseToken.equals(requestToken))
		{
			requestContext.abortWith(AbortResponses.UNAUTHORIZED_RESPONSE);
			//throw new RuntimeException("token is invalid");
		}
		LOG.debug("########################## " + SIGNATURE + " ##########################");
		LOG.debug("########################## " + responseToken + " ##########################");
	}
}