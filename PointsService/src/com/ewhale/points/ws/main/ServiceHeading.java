/**
 * 
 */
package com.ewhale.points.ws.main;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.core.MediaType;

/**
 * @author Ayman Yosry
 */
public interface ServiceHeading
{
	String SUCCESS_RESULT = "<result>success</result>";

	String FAILURE_RESULT = "<result>failure</result>";

	String SUPPORTED_OPERATIONS = "<operations>GET, PUT, POST, DELETE</operations>";

	String MEDIA_TYPE = MediaType.APPLICATION_JSON;
	// String MEDIA_TYPE = MediaType.APPLICATION_XML;

	String MEDIA_TYPE_FORM = MediaType.APPLICATION_FORM_URLENCODED;

	public static final ArrayList<Map<String,Object>> emptyList = new ArrayList<>();
}
