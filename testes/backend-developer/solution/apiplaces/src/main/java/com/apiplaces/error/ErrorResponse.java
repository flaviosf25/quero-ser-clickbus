package com.apiplaces.error;

public class ErrorResponse {
	
	 private  String message;
	 private  int erroCode;
	 
	 public ErrorResponse(String message, int code) {
		 this.message = message;
		 this.erroCode = code;	
	 }

	public String getMessage() {
		return message;
	}

	public int getErroCode() {
		return erroCode;
	}

}
