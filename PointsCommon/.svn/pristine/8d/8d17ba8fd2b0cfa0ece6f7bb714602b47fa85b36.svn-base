/**
 * 
 */
package com.ewhale.points.common.security.cms.android;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.util.AppConstants;

/**
 * @author Ayman Yosry
 *
 */
public class DataVerifier extends com.ewhale.points.common.security.cms.DataVerifier
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String publicKey = null;

	/**
	 * 
	 * @param data
	 * @param sigToVerify
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@Override
	public boolean verify(byte[] data, byte[] sigToVerify) throws AuthenticationSecurityException
	{
		boolean isVerified = false;
		byte[] encPublicKey;
		try
		{
			encPublicKey = b64.decodeBuffer(publicKey);
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encPublicKey);
			KeyFactory keyFactory = KeyFactory.getInstance(AppConstants.SecurityConstants.RSA_ALGORITHM);
			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
			
			Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
			sig.initVerify(pubKey);
			sig.update(data);
			isVerified = sig.verify(sigToVerify);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new AuthenticationSecurityException("XXXXXX  NoSuchAlgorithmException", e);
		}
		catch (InvalidKeyException e)
		{
			throw new AuthenticationSecurityException("XXXXXX  InvalidKeyException", e);
		}
		catch (SignatureException e)
		{
			throw new AuthenticationSecurityException("XXXXXX  SignatureException", e);
		}
		catch (InvalidKeySpecException e)
		{
			throw new AuthenticationSecurityException("XXXXXX  InvalidKeySpecException", e);
		}
		catch (IOException e)
		{
			throw new AuthenticationSecurityException("XXXXXX  Problem in B64 Decoder", e);
		}

		return isVerified;
	}

	public void setPublicKey(String publicKey)
	{
		this.publicKey = publicKey.replace(AppConstants.SecurityConstants.SIGNATURE_SEPARATOR, "\n");
	}
}