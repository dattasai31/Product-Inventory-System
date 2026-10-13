package com.example.demo.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeAspect {
	@Around("execution(* com.example.demo.Controller.ProductController.*(..))")
	public Object executionTime(ProceedingJoinPoint jp) throws Throwable
	{
		String method=jp.getSignature().getName();
		System.out.println("Execution started for this method: "+method);
		long start=System.currentTimeMillis();
		try
		{
			return jp.proceed();
		}
		finally
		{
			long end=System.currentTimeMillis();
			System.out.println("Method execution completed in: "+(end-start)+"ms");
			System.out.println("-----------------------------------------");
		}
	}
}
