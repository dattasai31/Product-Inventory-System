package com.example.demo.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handleFieldException(MethodArgumentNotValidException e)
	{
		Map<String,String> errors=new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error ->
		{
			errors.put(error.getField(), error.getDefaultMessage());
		}
				);
		return errors;
	}
	
	@ExceptionHandler(ProductNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleProductNotFoundException(ProductNotFound p)
	{
		return p.getMessage();
	}
	
	
	@ExceptionHandler(DuplicateProduct.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleDuplicateProduct(DuplicateProduct d)
	{
		return d.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGeneralException(Exception e)
	{
		return "Something went wrong";
	}

}
