/**
 * 
 */
package android.security;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import android.content.Context;

/**
 * @author Ayman Yosry
 *
 */
public class KeyPairGeneratorSpec implements AlgorithmParameterSpec ,Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static class Builder
	{
		public Builder(Context context){}
		public Builder setAlias(String string){return null;}
		public Builder setSubject(X500Principal x500Principal){return null;}
		public Builder setSerialNumber(BigInteger one){return null;}
		public Builder setStartDate(Date time){return null;}
		public Builder setEndDate(Date time){return null;}
		public Builder setKeyType(String rsaAlgorithm){return null;}
		public Builder setKeySize(int keySize){return null;}
		public KeyPairGeneratorSpec build(){return null;}
	}
}