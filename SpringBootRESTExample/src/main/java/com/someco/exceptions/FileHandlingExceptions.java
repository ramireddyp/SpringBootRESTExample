/**
 * 
 */
package com.someco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Venkat P
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="File Handling Exceptions")
public class FileHandlingExceptions extends Exception {

	private static final long serialVersionUID = -990092485226552374L;

	public FileHandlingExceptions(String message){
		super(message);
	}
}
