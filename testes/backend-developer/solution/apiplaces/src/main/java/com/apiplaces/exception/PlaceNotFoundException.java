package com.apiplaces.exception;

public class PlaceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private static final String message = "Place not found";

	public PlaceNotFoundException () {
		super(message);
	}

}
