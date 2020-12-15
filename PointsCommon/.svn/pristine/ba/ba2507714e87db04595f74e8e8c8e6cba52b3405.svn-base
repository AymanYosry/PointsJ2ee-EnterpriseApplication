/**
 * 
 */
package com.ewhale.points.common.token;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.jboss.logging.Logger;

import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.security.SecurityBuilder;

/**
 * @author Ayman Yosry
 */
public class TokenGenerator implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Logger LOG = Logger.getLogger(TokenGenerator.class);

	/**
	 * @param tokenCredList
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	public String generate(List<String> tokenCredList) throws AuthenticationSecurityException
	{
		Calendar calendar = new GregorianCalendar();
		SecureRandom random = SecurityBuilder.createRandom();
		String tokenCred = "";
		for (Object input : tokenCredList)
		{
			tokenCred += input + ".";
		}

		String token = UUID.randomUUID().toString().toUpperCase() + "|" + random.nextLong() + "|" + tokenCred + "|" + calendar.getTimeInMillis();
		// token = securityBuilder.toHex(securityBuilder.hashSHA256(securityBuilder.encrypt(token)));
		token = SecurityBuilder.toHex(SecurityBuilder.hashSHA256(token));
		
		LOG.debug("token generated");
		return token;
	}
}
