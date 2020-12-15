/**
 * 
 */
package com.ewhale.points.common.stores;

import java.security.KeyStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ayman Yosry
 *
 */
public interface SecurityKeyStore
{
	public static final Map<String, KeyStore> KeyMap = Collections.synchronizedMap(new HashMap<String, KeyStore>());
}