package com.dzmitry.web_customer_tracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CustomerAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.dzmitry.web_customer_tracker.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.dzmitry.web_customer_tracker.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.dzmitry.web_customer_tracker.dao.*.*(..))")
    private void forDAOPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void beforeAppFlowAdvice(JoinPoint joinPoint){
        logger.info("======> beforeAppFlowAdvice: " + joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        for (Object arg: args){
            logger.info("======> " + arg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturningAppFlowAdvice(JoinPoint joinPoint, Object result){
        logger.info("======> afterReturningAppFlowAdvice: " + joinPoint.getSignature().toShortString());
        logger.info("======> result: " + result);
    }
}
