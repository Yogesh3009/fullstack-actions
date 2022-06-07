package com.fullstack.app.exception;


public class ServiceUnavailbleException extends Exception{

	
	public ServiceUnavailbleException (String errorMsg)
	{
		super(errorMsg);
	}
}
