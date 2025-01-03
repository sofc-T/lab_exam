package com.bookMid.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.labexam.controller.*.*(..))")
    public void logBeforeMethodCall(JoinPoint joinPoint) {
        String methodCalled = joinPoint.getSignature().getName();
        String classCalled = joinPoint.getTarget().getClass().getSimpleName();
        logger.info("method {} in class {} is called.", methodCalled, classCalled);
    }
}
