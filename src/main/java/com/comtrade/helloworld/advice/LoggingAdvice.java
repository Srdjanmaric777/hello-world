package com.comtrade.helloworld.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.comtrade.helloworld.controller.HelloWorldController.*(..)) " +
            "|| execution(* com.comtrade.helloworld.controller.HelloWorldRestController.*(..)) " +
            "|| execution(* com.comtrade.helloworld.controller.DBController.*(..))" +
            "|| execution(* com.comtrade.helloworld.controller.ExternalAPIController.*(..))")
    public void pointcutJoinPoint() {

    }

    @Pointcut(value = "execution(* com.comtrade.helloworld.controller.SecurityController.*(..))")
    public void pointcutProceed() {

    }

    @After("pointcutJoinPoint()")
    public void applicationLogger(JoinPoint joinPoint) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Method invoked : " +
                joinPoint.getTarget().getClass().getName() + " : " +
                joinPoint.getSignature().getName() + "()" +
                " - arguments : " +  mapper.writeValueAsString(joinPoint.getArgs()));
    }

    @Around("pointcutProceed()")
    public Object proceedLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        Object object = proceedingJoinPoint.proceed();
        logger.info("Method invoked : " + proceedingJoinPoint.getTarget().getClass().getName() + " : " +
                proceedingJoinPoint.getSignature().getName() + "()" +
                " - page : " +  mapper.writeValueAsString(object));
        return object;
    }
}
