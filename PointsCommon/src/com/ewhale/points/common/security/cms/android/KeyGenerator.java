package com.ewhale.points.common.security.cms.android;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import com.ewhale.points.common.exception.AuthenticationSecurityException;
import com.ewhale.points.common.util.AppConstants;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;

/**
 * @author Ayman Yosry
 */
public class KeyGenerator extends com.ewhale.points.common.security.cms.KeyGenerator
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KeyGenerator(String certificateAlias)
	{
		super(certificateAlias);
	}
	
	/**
	 * 
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@Override
	protected KeyStore loadKeystore() throws AuthenticationSecurityException
	{
		KeyStore keystore = null;
		try
		{
			keystore = KeyStore.getInstance(AppConstants.SecurityConstants.ANDROID_KEYSTORE_INSTANCE);
			keystore.load(null);
		}
		catch (KeyStoreException e)
		{
			throw new AuthenticationSecurityException("Problem in loading keystore ...", e);
		}
		catch (CertificateException | NoSuchAlgorithmException | IOException e)
		{
			throw new AuthenticationSecurityException("Problem in loading certificate ...", e);
		}
		return keystore;
	}

	/**
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	@Override
	public PrivateKey getPrivateKey() throws AuthenticationSecurityException
	{
		PrivateKey privateKey;
		try
		{
			KeyStore keystore = getKeyPair();
			KeyStore.Entry entry = keystore.getEntry(certificateAlias, null);
			if (!(entry instanceof KeyStore.PrivateKeyEntry))
			{
				//System.out.println("Not an instance of a PrivateKeyEntry");
				return null;
			}
			privateKey = ((KeyStore.PrivateKeyEntry) entry).getPrivateKey();
		}
		catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException e)
		{
			throw new AuthenticationSecurityException("IO Problem in exporting private key ...", e);
		}
		return privateKey;
	}

	/**
	 *
	 * @return
	 * @throws AuthenticationSecurityException
	 */
	public void loadCertificate() throws AuthenticationSecurityException
	{
		Certificate cert;
		try
		{
			CertificateFactory cf = CertificateFactory.getInstance(AppConstants.SecurityConstants.CERT_FACTORY);
			InputStream certstream = KeyGenerator.class.getResourceAsStream(AppConstants.SecurityConstants.AUTH_CERT_FILE);
			cert = cf.generateCertificate(certstream);
			// Add the certificate
			getKeyPair().setCertificateEntry(AppConstants.SecurityConstants.AUTH_CERT_ALIAS, cert);
		}
		catch (CertificateException | KeyStoreException e)
		{
			throw new AuthenticationSecurityException("Problem in loading Certificate ...", e);
		}
	}

	/**
	 * Generate a new RSA key pair entry in the Android Keystore by using the
	 * KeyPairGenerator API. The private key can only be  used for signing or
	 * verification and only with SHA-256 or  SHA-512 as the message digest.  
	 * 
	 * @param context
	 * @throws AuthenticationSecurityException 
	 */
	@SuppressWarnings("deprecation")
	public void loadPrivatekey(Object context) throws AuthenticationSecurityException
	{
		try
		{
			KeyPairGeneratorSpec spec;
			spec = new KeyPairGeneratorSpec.Builder((Context) context)
					.setAlias(certificateAlias)
					.setSubject(new X500Principal(AppConstants.SecurityConstants.DS_CERT_ISSUER))
					.setSerialNumber(new BigInteger(AppConstants.SecurityConstants.DS_CERT_SERIAL))
					.setKeyType(AppConstants.SecurityConstants.RSA_ALGORITHM)
					.setKeySize(AppConstants.SecurityConstants.KEY_SIZE)
					.setStartDate(new Date(AppConstants.SecurityConstants.DS_CERT_START_DATE))
					.setEndDate(new Date(AppConstants.SecurityConstants.DS_CERT_END_DATE)).build();
			KeyPairGenerator generator = KeyPairGenerator.getInstance(
					AppConstants.SecurityConstants.RSA_ALGORITHM,
					AppConstants.SecurityConstants.ANDROID_KEYSTORE_INSTANCE);
			generator.initialize(spec);
			generator.generateKeyPair();
		}
		catch (Exception e)
		{
			throw new AuthenticationSecurityException("Problem in loading PrivateKey ...", e);
		}
	}

	/**
	 * 
	 * @return
	 * @throws AuthenticationSecurityException 
	 */
	public List<String> getKeys() throws AuthenticationSecurityException
	{
		List<String> aliasesList = null;
		try
		{
			KeyStore keyStore = KeyStore.getInstance(AppConstants.SecurityConstants.ANDROID_KEYSTORE_INSTANCE);
			keyStore.load(null);

			Enumeration<String> aliases = keyStore.aliases();
			String certificateAlias = null;
			aliasesList = new ArrayList<>();
			while (aliases.hasMoreElements())
			{
				certificateAlias = aliases.nextElement();
				//System.out.println(certificateAlias);
				aliasesList.add(certificateAlias);
			}
		}
		catch (Exception e)
		{
			throw new AuthenticationSecurityException("Problem in loading Keystore aliases ...", e);
		}
		return aliasesList;
	}
}
