package com.example.demo.ExceptionHandling;

public class ProductNotFound extends RuntimeException {
	public ProductNotFound(String message)
	{
		super(message);
	}

}
