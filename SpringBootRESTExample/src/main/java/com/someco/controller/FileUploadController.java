/**
 * 
 */
package com.someco.controller;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.someco.constants.ApplicationConstants;
import com.someco.exceptions.FileHandlingExceptions;
import com.someco.service.FileServices;
import com.someco.utils.FileUtil;

/**
 * @author Venkat P
 *
 */
@RestController
public class FileUploadController {

	public static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private FileServices fileService;


	@PostMapping("/upload")	
	public String uploadFile(@RequestParam(value = "file") MultipartFile multipartFile) throws Exception {
		//try{
			/**
			 * This will provide standard file metadata attributes defined by business
			 */
			Map<String, String> fileMetaDataAttributes = FileUtil.frameMetaDataAttributes();
			String filePath = ApplicationConstants.TMP_DIR+multipartFile.getOriginalFilename();

			File file = FileUtil.getFilefromStream(filePath, multipartFile.getInputStream());

			/**
			 * this services will create metadata attribues on file and persist the file in File system
			 */
			fileService.persistFile(file, fileMetaDataAttributes);

		/*} catch (FileHandlingExceptions e) {
			logger.error(e.getMessage());
			return "Failed to upload " + multipartFile.getOriginalFilename() + ": " + e.getMessage();
		} catch (Exception e){
			logger.error(e.getMessage());
			return "Exception occured while processing your file"+ multipartFile.getOriginalFilename() + ": " + e.getMessage();
		}*/
		return "File Succesfully upload to following location " + filePath;

	}


	@GetMapping("/getMetaData/{fileName:.+}")
	public String getFileMetaDataAttributes(@PathVariable(value = "fileName", required = true) String fileName,
			HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws FileHandlingExceptions, Exception{
		
		return fileService.getFileMetaData(ApplicationConstants.TMP_DIR+fileName).toString();

	}


	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> doDownloadFile(HttpServletRequest request,HttpServletResponse response, @PathVariable("fileName") String fileName) throws Exception{

		File file = new File(ApplicationConstants.TMP_DIR+fileName);
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		String mimeType= URLConnection.guessContentTypeFromName(file.getName());

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType(mimeType))
				.body(resource);
	}

	/**
	 * This method will handle all Exceptions of tyeps FileHandlingExceptions class
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(FileHandlingExceptions.class)
	public String handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
		return ex.getMessage();
	}

}
