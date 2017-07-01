package com.someco.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalApplicationExceptions {

	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerExceptions(HttpServletRequest request, Exception ex){
		return "Null pointer excepitons occured"+ex.getMessage();
	}

	@ExceptionHandler(IOException.class)
	public String handleIOExceptions(HttpServletRequest request, Exception ex){
		return "Null pointer excepitons occured"+ex.getMessage();
	}


	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="General Exceptions")
	@ExceptionHandler(Exception.class)
	public String handleGeneralExceptions(){
		return "Plase contact system administrators";
	}
}
