/**
 * 
 */
package com.ewhale.points.common.util;

import java.text.SimpleDateFormat;

/**
 * @author Ayman Yosry
 */
public interface AppConstants
{
	// IMP_Ayman IMP_Ahmed IMP_Ahmed this data should be read from property file under wildfly and android
	int KEY_SIZE = 1024; 
	
	String SESSION_KEY = "jCryptionKeys";

	// String HOSTNAME = "localhost";
	String HOSTNAME = "139.162.219.122";

	String SERVICE_MAIN_PATH = "/PointsService/rest";

	String WEBPROTOCOL = "http";

	String PORT = "8080";

	String RESPONSE_TYPE = "application/json";

	String EMPTY_STRING = "";

	String ERROR_PAGE = WEBPROTOCOL+"://"+HOSTNAME+":"+PORT+"/PointsWeb/ControllerServlet";

	String LOGIN_SERVICE = "login";

	String dateFormatStr = "dd-MM-yyyy";

	String dateTimeFormatStr = "dd-MM-yyyy hh:mm";

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateTimeFormatStr);

	SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);

	// Security Constants
	interface SecurityConstants
	{
		int CPU_COST = 16;

		int MEMORY_COST = 16;

		int PARALLELIZATION = 16;

		String SHA256withRSA_ALGORITHM = "SHA256withRSA";

		String SHA1withRSA_ALGORITHM = "SHA1withRSA";

		String SHA256_ALGORITHM = "SHA-256";

		String SHA512_ALGORITHM = "SHA-512";

		String SHA1_ALGORITHM = "SHA-1";

		String RSA_ALGORITHM = "RSA";
		
		int KEY_SIZE = 2048;

		String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";// "RSA/ECB/NoPadding";

		String KEYSTORE_INSTANCE = "JKS";
		
		String ANDROID_KEYSTORE_INSTANCE = "AndroidKeyStore";

		String PROVIDER = "SC"; // "BC";
		
		String CERT_FACTORY = "X.509";
		
		// Digital Signature Certificate Information
		String DS_KEYSTORE = "/com/ewhale/points/common/security/keystore/PointsDSKeystore.jks";
		String DS_TRUST_KEYSTORE = "/com/ewhale/points/common/security/keystore/PointsDSTrustKeystore.jks";
		String DS_KEYSTORE_PWD = "ewKeystorePass123";
		String DS_PRIVATEKEY_PWD = "ewPrivkeyPass123";
		String DS_CERT_ALIAS = "POINTSDSKS";
		String DS_CERT_ISSUER = "CN=egyptwhale.com, OU=Mobile, O=EgyptWhale, L=Cairo, ST=Cairo, C=EG";
		String DS_CERT_SERIAL = "581496788";
		String DS_CERT_START_DATE = "11/01/2016 13:24:33";
		String DS_CERT_END_DATE = "11/01/2018 13:24:33";

		// Authentication Certificate Information
		String AUTH_KEYSTORE = "/com/ewhale/points/common/security/keystore/PointsAuthKeystore.jks";
		String AUTH_TRUST_KEYSTORE = "/com/ewhale/points/common/security/keystore/PointsAuthTrustKeystore.jks";
		String AUTH_KEYSTORE_PWD = "ewKeystorePass123";
		String AUTH_CERT_FILE = "PointsAuthCert.cer";
		String AUTH_PRIVATEKEY_PWD = "ewPrivkeyPass123";
		String AUTH_CERT_ALIAS = "POINTSAUTHKS";

		String ENCODE_TYPE = "UTF-16LE";

		String ENCODE_TYPE_8 = "UTF-8";

		int PASSWORD_BITS = 70;

		int PASSWORD_RADIX = 32;

		String SIGNATURE = "signature";
		String SECURITY_BUILDER = "sb";

		String SIGNATURE_SEPARATOR = "-!!-";

		String TOKEN = "token";

		int TOKEN_TIME_OUT = -60;

		String PK = "PK";
	}// End
}
