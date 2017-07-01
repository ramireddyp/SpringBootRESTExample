/**
 * 
 */
package com.someco.service.impl;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.someco.exceptions.FileHandlingExceptions;
import com.someco.service.FileServices;

/**
 * @author Venkat P
 *
 */
@Service
public class FileSerivesImpl implements FileServices {

	/* (non-Javadoc)
	 * @see com.someco.service.FileServices#persistFile(java.io.File, java.util.Map, java.lang.String)
	 */

	@Override
	public void persistFile(File file, Map<String, String> metaData) throws FileHandlingExceptions, Exception{
		if(file == null){
			throw new FileHandlingExceptions("file not found");
		}
		UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
		if(metaData != null && !metaData.isEmpty()){
			//metaData.forEach((k,v)->view.write(k, Charset.defaultCharset().encode(v)));
			for (Map.Entry<String, String> entry : metaData.entrySet()) {
				view.write(entry.getKey(), Charset.defaultCharset().encode(entry.getValue()));
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.someco.service.FileServices#getFileMetaData(java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject getFileMetaData(String filePath) throws FileHandlingExceptions, Exception {
		File file = new File(filePath);
		UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(),UserDefinedFileAttributeView.class);
		List<String> listOfAttributes = view.list();
		if(listOfAttributes == null){
			throw new FileHandlingExceptions("Attributes not fuound on the selected file name");
		}
		JSONObject metaDataJson= new JSONObject();
		if(listOfAttributes != null && !listOfAttributes.isEmpty()){
			for(String name : listOfAttributes){
				ByteBuffer buf = ByteBuffer.allocate(view.size(name));
				view.read(name, buf);
				buf.flip();
				String data = Charset.defaultCharset().decode(buf).toString();
				metaDataJson.put(name, data);
			}

		}
		return metaDataJson;
	}
}
