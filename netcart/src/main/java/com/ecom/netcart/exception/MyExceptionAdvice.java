package com.ecom.netcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ecom.netcart.model.ErrorResponse;

@ControllerAdvice
public class MyExceptionAdvice {
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e,WebRequest we){
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(404);
		er.setErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e,WebRequest we){
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(404);
		er.setErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException e,WebRequest we){
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(404);
		er.setErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e,WebRequest we){
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(404);
		er.setErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<ErrorResponse> handleInvalidLoginException(InvalidLoginException e,WebRequest we){
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(404);
		er.setErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidRegistrationException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRegistrationException(InvalidRegistrationException e,WebRequest we){
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(404);
		er.setErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	}
}
