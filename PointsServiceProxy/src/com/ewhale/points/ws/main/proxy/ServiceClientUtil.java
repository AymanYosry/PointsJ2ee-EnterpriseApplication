package com.ewhale.points.ws.main.proxy;

import java.io.Serializable;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.URLConnectionEngine;

import com.ewhale.points.common.security.SecurityFactory;
import com.ewhale.points.common.util.AppConstants;
import com.ewhale.points.ws.agent.proxy.AgentAdminServiceClient;
import com.ewhale.points.ws.agent.proxy.AgentSellerServiceClient;
import com.ewhale.points.ws.interceptors.ServiceClientDigitalSignaturePreInterceptor;
import com.ewhale.points.ws.interceptors.ServiceClientTokenVerificationInterceptor;
import com.ewhale.points.ws.security.proxy.AuthenticationServiceClient;
import com.ewhale.points.ws.system.proxy.SystemAdminServiceClient;
import com.ewhale.points.ws.system.proxy.SystemSalesServiceClient;
import com.ewhale.points.ws.user.proxy.UserServiceClient;

public class ServiceClientUtil implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ANDROID = SecurityFactory.CMS_ANDROID_SECURITY_BUILDER;
	public static final String DESKTOP = SecurityFactory.CMS_SECURITY_BUILDER;
	public static final String WEB = SecurityFactory.CMS_SECURITY_BUILDER;

	private static ResteasyWebTarget getWebServiceTarget()
	{
		URLConnectionEngine engine = new URLConnectionEngine();
		ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
		ResteasyWebTarget target = client
				.target(AppConstants.WEBPROTOCOL + "://" + AppConstants.HOSTNAME + ":" + AppConstants.PORT + AppConstants.SERVICE_MAIN_PATH);
		return target;
	}

	public static AgentAdminServiceClient getAgentAdminServiceClient(String token)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		AgentAdminServiceClient agentAdminServiceClient = target.proxy(AgentAdminServiceClient.class);
		return agentAdminServiceClient;
	}

	public static AgentSellerServiceClient getAgentSellerServiceClient(String token)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		AgentSellerServiceClient agentSellerServiceClient = target.proxy(AgentSellerServiceClient.class);
		return agentSellerServiceClient;
	}
	
	public static AgentSellerServiceClient getAgentSellerServiceClient(String token, String clientType)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		target.register(new ServiceClientDigitalSignaturePreInterceptor(clientType));
		AgentSellerServiceClient agentSellerServiceClient = target.proxy(AgentSellerServiceClient.class);
		return agentSellerServiceClient;
	}

	public static SystemSalesServiceClient getSystemSalesServiceClient(String token)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		SystemSalesServiceClient systemSalesServiceClient = target.proxy(SystemSalesServiceClient.class);
		return systemSalesServiceClient;
	}

	public static SystemAdminServiceClient getSystemAdminServiceClient(String token)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		SystemAdminServiceClient systemAdminServiceClient = target.proxy(SystemAdminServiceClient.class);
		return systemAdminServiceClient;
	}
	
	public static SystemAdminServiceClient getSystemAdminServiceClient(String token, String clientType)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		target.register(new ServiceClientDigitalSignaturePreInterceptor(clientType));
		SystemAdminServiceClient systemAdminServiceClient = target.proxy(SystemAdminServiceClient.class);
		return systemAdminServiceClient;
	}

	public static UserServiceClient getUserServiceClient(byte clientType)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		UserServiceClient userServiceClient = target.proxy(UserServiceClient.class);
		return userServiceClient;
	}

	public static UserServiceClient getUserServiceClient(String token)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		UserServiceClient userServiceClient = target.proxy(UserServiceClient.class);
		return userServiceClient;
	}

	public static SynchronizationServiceClient getSynchronizationServiceClient(String token)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		SynchronizationServiceClient synchronizationServiceClient = target.proxy(SynchronizationServiceClient.class);
		return synchronizationServiceClient;
	}

	public static LookUpServiceClient getLookUpServiceClient(String token)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientTokenVerificationInterceptor(token));
		LookUpServiceClient lookUpServiceClient = target.proxy(LookUpServiceClient.class);
		return lookUpServiceClient;
	}

	public static AuthenticationServiceClient getAuthenticationServiceClient(String clientType)
	{
		ResteasyWebTarget target = ServiceClientUtil.getWebServiceTarget();
		target.register(new ServiceClientDigitalSignaturePreInterceptor(clientType));
		AuthenticationServiceClient authenticationServiceClient = target.proxy(AuthenticationServiceClient.class);
		return authenticationServiceClient;
	}
}
