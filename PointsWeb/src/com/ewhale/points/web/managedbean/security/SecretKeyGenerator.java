/**
 * 
 */
package com.ewhale.points.web.managedbean.security;

import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.ewhale.points.web.managedbean.main.AbsoluteBean;

/**
 * @author Ayman Yosry
 */
@RequestScoped
@ManagedBean
public class SecretKeyGenerator extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String secretKey;

	/**
	 */
	@Override
	public void handlePostConstruct()
	{
		super.handlePostConstruct();
		secretKey = UUID.randomUUID().toString();
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey()
	{
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey)
	{
		this.secretKey = secretKey;
	}
}