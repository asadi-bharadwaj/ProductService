package com.productService.ProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.productService.ProductService.model.ErrorResponse;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductServiceCustomException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException exception) {
		return new ResponseEntity<>(new ErrorResponse().builder().errorMessage(exception.getMessage())
				.errorCode(exception.getErrorCode()).build(), HttpStatus.NOT_FOUND);
	}
}
