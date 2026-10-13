package com.example.demo.ExceptionHandling;

public class DuplicateProduct extends RuntimeException{
	public DuplicateProduct(String message)
	{
		super(message);
	}

}
