package com.ewhale.points.common.security;

import java.io.Serializable;

import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.security.cms.DataDecryptor;
import com.ewhale.points.common.security.cms.DataEncryptor;
import com.ewhale.points.common.security.cms.DataSigner;
import com.ewhale.points.common.security.cms.DataVerifier;

class CMSSecurityBuilder extends SecurityBuilder implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Sign Data
	 * 
	 * @param data
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@Override
	public String sign(String data) throws AuthenticationSecurityException
	{
		DataSigner signer = new DataSigner();
		String signature = signer.sign(data);
		return signature;
	}

	/**
	 * Verify Data
	 * 
	 * @param data
	 * @param signature
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@Override
	public boolean verify(String data, String signature) throws AuthenticationSecurityException
	{
		DataVerifier verifier = new DataVerifier();
		boolean isVerified = verifier.verify(data, signature);
		return isVerified;
	}

	/**
	 * @param password
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@Override
	public String encrypt(String password) throws AuthenticationSecurityException
	{
		DataEncryptor enc = new DataEncryptor();
		password = toHex(hashSHA256(password));
		String encryptedPassword = enc.encrypt(password);
		return encryptedPassword;
	}

	/**
	 * @param password
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@Override
	public String decrypt(String password) throws AuthenticationSecurityException
	{
		DataDecryptor dec = new DataDecryptor();
		String decryptedPassword = dec.decrypt(password);
		return decryptedPassword;
	}
}
