/**
 * 
 */
package com.someco.service;

import java.io.File;
import java.util.Map;

import org.json.JSONObject;

import com.someco.exceptions.FileHandlingExceptions;

/**
 * @author Venkat P
 *
 */
public interface FileServices {
	public void persistFile(File file, Map<String, String> metaData) throws FileHandlingExceptions, Exception;
	public JSONObject getFileMetaData(String filePath) throws FileHandlingExceptions, Exception;
}
