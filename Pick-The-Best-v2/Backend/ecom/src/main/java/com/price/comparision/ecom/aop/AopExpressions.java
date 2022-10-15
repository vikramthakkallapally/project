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
	
	@Pointcut("execution(* com..verifyOtp*(..))")
    public void forVerifyOtp() {
    }
	
	@Pointcut("execution(* com..reset*(..))")
    public void resetPassword() {
    }
	
	@Pointcut("execution(* com..register*(..))")
    public void register() {
    }
	
	@Pointcut("execution(* com..login*(..))")
    public void login() {
    }
	
	@Pointcut("execution(* com..getEncodedPassword*(..))")
    public void getEncoded() {
    }
    
}
