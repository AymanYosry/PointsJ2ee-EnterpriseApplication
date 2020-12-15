package com.ewhale.points.common.security.cms;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.security.base64.BASE64Decoder;
import com.ewhale.points.common.security.base64.BASE64Encoder;
import com.ewhale.points.common.util.AppConstants;

/**
 * @author Ayman Yosry
 */
public class DataSigner implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String SIGNATURE_ALGORITHM = AppConstants.SecurityConstants.SHA256withRSA_ALGORITHM;
	private String KEYSTORE = AppConstants.SecurityConstants.DS_KEYSTORE;
	private String KEYSTORE_PWD = AppConstants.SecurityConstants.DS_KEYSTORE_PWD;
	protected String CERT_ALIAS = AppConstants.SecurityConstants.DS_CERT_ALIAS;
	private String PRIVATEKEY_PWD = AppConstants.SecurityConstants.DS_PRIVATEKEY_PWD;
	protected KeyGenerator keyPair = null;
	/**
	 * Initialize Security Provider
	 */
	public DataSigner()
	{
		keyPair = new KeyGenerator(KEYSTORE, KEYSTORE_PWD, CERT_ALIAS, PRIVATEKEY_PWD);
	}

	/**
	 * Method to sign given information
	 * 
	 * @param data
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	public String sign(final String data) throws AuthenticationSecurityException
	{
		BASE64Decoder b64 = new BASE64Decoder();
		String signature;
		final String stringToSign = data.replaceAll("\\r\\n", "\\n");
		byte[] dataToSign;
		
		try
		{
			dataToSign = b64.decodeBuffer(stringToSign);
			signature = sign(dataToSign);
		}
		catch (IOException e)
		{
			throw new AuthenticationSecurityException(e);
		}

		signature = signature.replace("\r\n", AppConstants.SecurityConstants.SIGNATURE_SEPARATOR).replace("\n", AppConstants.SecurityConstants.SIGNATURE_SEPARATOR);
		return signature;
	}

	public String sign(byte data[]) throws AuthenticationSecurityException
	{
		String signature = null;
		try
		{
			Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
			PrivateKey priv = keyPair.getPrivateKey();
			sig.initSign(priv);
			sig.update(data);

			byte[] der = sig.sign();
			BASE64Encoder encoder = new BASE64Encoder();
			signature = encoder.encode(der);
		}
		catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e)
		{
			throw new AuthenticationSecurityException(e);
		}
		return signature;
	}
}