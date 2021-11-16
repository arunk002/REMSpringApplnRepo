package com.mph.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void controller() {

	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void restController() {

	}
	@Around("restController()")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println(joinPoint.getSignature().getDeclaringTypeName());
		joinPoint.proceed();
		System.out.println("Done :)");
	}
}
