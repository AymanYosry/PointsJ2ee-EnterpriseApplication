/**
 * 
 */
package com.ewhale.points.common.stores;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author AymanYosry
 */
public interface TokentStore
{
	public static final Map<String, String[]> TokenMap = new ConcurrentHashMap<>();
}