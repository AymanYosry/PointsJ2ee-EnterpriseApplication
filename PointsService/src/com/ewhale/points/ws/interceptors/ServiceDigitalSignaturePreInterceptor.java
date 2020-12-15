/**
 * 
 */
package com.ewhale.points.ws.interceptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import org.jboss.logging.Logger;

import com.ewhale.points.common.annotations.VerifyDigitaSignature;
import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.security.SecurityFactory;
import com.ewhale.points.common.security.base64.BASE64Encoder;
import com.ewhale.points.common.util.AppConstants;
import com.ewhale.points.common.util.ExceptionConstants;

/**
 * @author Ayman Yosry
 * @updated Ahmad Khalil
 */

@Provider
@Priority(value = 1)
@VerifyDigitaSignature
public class ServiceDigitalSignaturePreInterceptor implements ReaderInterceptor
{
	protected Logger logger = Logger.getLogger(ServiceDigitalSignaturePreInterceptor.class);

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext readerInterceptorContext)	throws IOException, WebApplicationException
	{
		BASE64Encoder b64 = new BASE64Encoder();
		String signature = readerInterceptorContext.getHeaders().getFirst(AppConstants.SecurityConstants.SIGNATURE);
		String securityBuilder = readerInterceptorContext.getHeaders().getFirst(AppConstants.SecurityConstants.SECURITY_BUILDER);
		
		InputStream inputStream = readerInterceptorContext.getInputStream();
		byte[] dataArr = new byte[1000];
		int dataRead = 0;
		ByteArrayOutputStream dataArrayOutputStream=new ByteArrayOutputStream();
		while((dataRead=inputStream.read(dataArr))!=-1)
		{
			logger.debug("############ we read "+dataRead+" bytes");
			dataArrayOutputStream.write(dataArr, 0, dataRead);
		}
		dataArrayOutputStream.flush();
		byte[] allDataArr=dataArrayOutputStream.toByteArray();
		dataArrayOutputStream.close();
		logger.debug("stream will close");
		inputStream.close();
		String data = b64.encode(allDataArr);

		logger.debug("############# Security Builder Indecator : " + securityBuilder);
		try
		{
			boolean isVerified = false;

			if(securityBuilder.equals(SecurityFactory.CMS_ANDROID_SECURITY_BUILDER))
			{
				String publicKey = readerInterceptorContext.getHeaders().getFirst(AppConstants.SecurityConstants.PK);
				isVerified = SecurityFactory.getSecurityBuilder(securityBuilder).verify(data, signature, publicKey);
			}
			else
			{
				isVerified = SecurityFactory.getSecurityBuilder(securityBuilder).verify(data, signature);
			}
			
			if (isVerified)
			{
				logger.debug("############# Signature verified");
			}
			else
			{
				logger.debug("############# Signature verification failed");
				Response response = Response.status(ExceptionConstants.SECURITY_EXCETION_STATUS_CODE)
						.entity(ExceptionConstants.INVALID_DIGITAL_SIGNATURE_EX_MSG)
						.type(ExceptionConstants.EXCEPTION_MESSAGE_TYPE).build();
				throw new WebApplicationException("Problem While verifying signature", response);
			}
		}
		catch (AuthenticationSecurityException e)
		{
			logger.error("############# problem checking signature", e);
			Response response = Response.status(e.getStatusCode()).entity(e.getMessage())
					.type(ExceptionConstants.EXCEPTION_MESSAGE_TYPE).build();
			throw new WebApplicationException("Problem While retrieving data", response);
		}
		readerInterceptorContext.setInputStream(new ByteArrayInputStream(allDataArr));

		return readerInterceptorContext.proceed();
	}
}
