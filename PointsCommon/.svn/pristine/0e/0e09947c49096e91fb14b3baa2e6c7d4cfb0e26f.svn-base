/**
 * 
 */
package com.ewhale.points.common.security.cms;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.security.base64.BASE64Decoder;
import com.ewhale.points.common.util.AppConstants;

/**
 * @author Ayman Yosry
 * @Date 01/07/2015
 *
 */
public class DataVerifier implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String KEYSTORE = AppConstants.SecurityConstants.DS_TRUST_KEYSTORE;
	private String KEYSTORE_PWD = AppConstants.SecurityConstants.DS_KEYSTORE_PWD;
	private String CERT_ALIAS = AppConstants.SecurityConstants.DS_CERT_ALIAS;
	protected String SIGNATURE_ALGORITHM = AppConstants.SecurityConstants.SHA256withRSA_ALGORITHM;
	protected BASE64Decoder b64 = new BASE64Decoder();

	/**
	 * Method to verify given signed data
	 * 
	 * @param data
	 * @param signature
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	public boolean verify(final String data, final String signature) throws AuthenticationSecurityException
	{
		boolean isVerified = false;
		final String stringToVerify = data.replaceAll("\\r\\n", "\\n");
		final String dataSignature = signature.replace(AppConstants.SecurityConstants.SIGNATURE_SEPARATOR, "\n");

		byte[] dataToVerify;
		byte[] sign;
		try
		{
			dataToVerify = b64.decodeBuffer(stringToVerify);
			sign = b64.decodeBuffer(dataSignature);
			isVerified = verify(dataToVerify, sign);
		}
		catch (IOException e)
		{
			throw new AuthenticationSecurityException(e);
		}

		return isVerified;
	}

	/**
	 * 
	 * @param data
	 * @param sigToVerify
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	public boolean verify(byte[] data, byte[] sigToVerify) throws AuthenticationSecurityException
	{
		KeyGenerator keyPair = new KeyGenerator(KEYSTORE, KEYSTORE_PWD, CERT_ALIAS);

		boolean isVerified = false;
		try
		{
			PublicKey pubKey = keyPair.getPublicKey();
			Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
			sig.initVerify(pubKey);
			sig.update(data);
			isVerified = sig.verify(sigToVerify);
		}
		catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e)
		{
			throw new AuthenticationSecurityException(e);
		}

		return isVerified;
	}
}
