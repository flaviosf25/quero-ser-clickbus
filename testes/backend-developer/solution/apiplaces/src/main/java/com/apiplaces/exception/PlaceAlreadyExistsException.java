package com.apiplaces.exception;

public class PlaceAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private static final String message = "Place already exists";

	public PlaceAlreadyExistsException () {
		super(message);
	}

}
