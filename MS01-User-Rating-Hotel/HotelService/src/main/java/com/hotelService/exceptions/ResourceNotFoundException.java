package com.hotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException 
{
	public ResourceNotFoundException()
	{
		super("Resource Not found by given Id !!");
	}
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}

}
