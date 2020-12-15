package com.ewhale.points.common.security.cms.android;

/**
 * @author Ayman Yosry
 */
public class DataEncryptor extends com.ewhale.points.common.security.cms.DataEncryptor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataEncryptor()
	{
		keyPair = new KeyGenerator(CERT_ALIAS);
	}
}