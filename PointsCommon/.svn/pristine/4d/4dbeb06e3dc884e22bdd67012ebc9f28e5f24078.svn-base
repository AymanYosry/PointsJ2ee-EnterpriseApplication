/**
 * 
 */
package com.ewhale.points.common.security;

import java.util.HashMap;
import java.util.Map;

import com.ewhale.points.common.exception.AuthenticationSecurityException;

/**
 * @author Ayman Yosry
 * @updated Ahmed Khalil
 */
public class SecurityFactory
{
	private static Map<String,SecurityBuilder> securityBuilderMap = new HashMap<>();

	public static final String CMS_ANDROID_SECURITY_BUILDER = "1";
	public static final String CMS_SECURITY_BUILDER = "2";
	//public static final String RSA_SECURITY_BUILDER = "3";

	public static SecurityBuilder getSecurityBuilder(String securityBuilderType) throws AuthenticationSecurityException
	{
		SecurityBuilder securityBuilder=securityBuilderMap.get(securityBuilderType);
		if (securityBuilder!= null) return securityBuilder;
		switch (securityBuilderType)
		{
			case CMS_ANDROID_SECURITY_BUILDER:
				securityBuilder = new CMSAndroidSecurityBuilder();
				break;
			case CMS_SECURITY_BUILDER:
				securityBuilder = new CMSSecurityBuilder();
				break;
			default:
				break;
		}
		securityBuilderMap.put(securityBuilderType, securityBuilder);
		return securityBuilder;
	}
}
