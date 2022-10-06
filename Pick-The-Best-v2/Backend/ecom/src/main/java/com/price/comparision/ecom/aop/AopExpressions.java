package com.price.comparision.ecom.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
	
	@Pointcut("execution(* com.price.comparision.ecom.controller.*.*(..))")
	public void forController(){	
	}
	
	@Pointcut("execution(* com.price.comparision.ecom.service.*.*(..))")
	public void forService() {
	}
	
	@Pointcut("execution(* com.price.comparision.ecom.scheduledTasks.*.*(..))")
	public void forScheduledTasks() {
	}
	
	
}
