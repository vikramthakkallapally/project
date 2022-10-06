package com.price.comparision.ecom.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
@ComponentScan("com.price.comparision.ecom")
public class AspectLogger {
	
	private Logger myLogger = Logger.getLogger(AspectLogger.class.getName());
	
	@Around("com.price.comparision.ecom.aop.AopExpressions.forController()")
	public Object aroundController(ProceedingJoinPoint joinpoint) throws Throwable{
		
		String method = joinpoint.getSignature().toShortString();
		myLogger.info(" ---> Execution @Around advice on "+method);
		long begin = System.currentTimeMillis();
		Object result = joinpoint.proceed();
		long end = System.currentTimeMillis();
		long duration = end-begin;
		myLogger.info(" ---> The Result of method "+method+"   "+result);
		myLogger.info(" ---> The Total Duration : "+(duration/1000));
		return result;
	}
	
	
	@Around("com.price.comparision.ecom.aop.AopExpressions.forService()")
	public Object aroundService(ProceedingJoinPoint joinpoint) throws Throwable{
		
		String method = joinpoint.getSignature().toShortString();
		myLogger.info(" ---> Execution @Around advice on "+method);
		long begin = System.currentTimeMillis();
		Object result = joinpoint.proceed();
		long end = System.currentTimeMillis();
		long duration = end-begin;
		myLogger.info(" ---> The Result of method "+method+"   "+result);
		myLogger.info(" ---> The Total Duration : "+(duration/1000));
		
		return result;
	}
	
	@Before("com.price.comparision.ecom.aop.AopExpressions.forScheduledTasks()")
	public void beforeAddAccountAdvice(JoinPoint joinpoint) {
		myLogger.info(" ---> Execution @Before advice on executing Task()");
		
		MethodSignature methodSign = (MethodSignature) joinpoint.getSignature();
		myLogger.info(" ---> Method Signature : "+ methodSign);
		
		Object[] arguments = joinpoint.getArgs();
	
		myLogger.info("     ---> Method Argument : "+arguments);
		
	}
	
	
}
