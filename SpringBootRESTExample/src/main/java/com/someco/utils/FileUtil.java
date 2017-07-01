/**
 * 
 */
package com.someco.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import com.someco.exceptions.FileHandlingExceptions;

/**
 * @author Venkat P
 *
 */
public class FileUtil {
	public static  Map<String, String> frameMetaDataAttributes(){
		Map<String, String> fileMetaDataAttributes = new HashMap<>();
		fileMetaDataAttributes.put("Company", "Someco");
		fileMetaDataAttributes.put("DocType", "Someco_Financial");
		fileMetaDataAttributes.put("Owner", "Venkat");
		fileMetaDataAttributes.put("Modifier", "Venkat");
		fileMetaDataAttributes.put("RevieByManager", "Yes");

		return fileMetaDataAttributes;
	}

	public static File getFilefromStream(String fileLocation, InputStream stream) throws IOException, FileHandlingExceptions{
		if(fileLocation == null || stream == null){
			throw new FileHandlingExceptions("Please verify either filePath or input stream is null");
		}
		File file = new File(fileLocation);
		FileOutputStream out = new FileOutputStream(file);
		Files.copy(file.toPath(), out);
		out.flush();
		out.close();
		stream.close();

		return file;
	}
}
