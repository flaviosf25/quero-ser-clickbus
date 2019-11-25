package com.apiplaces.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.apiplaces.exception.PlaceAlreadyExistsException;
import com.apiplaces.exception.PlaceNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Missing fields", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(PlaceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> placeAlreadyExists(PlaceAlreadyExistsException ex){
    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
	}
    
    @ExceptionHandler(PlaceNotFoundException.class)
    public ResponseEntity<ErrorResponse> placeNotExists(PlaceNotFoundException ex){
    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
