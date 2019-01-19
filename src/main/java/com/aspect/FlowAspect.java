package com.aspect;

import com.config.MvcConfiguration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class FlowAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("com.aspect.CRMLoggingAspect.appFlow()")
    public void beforeFlowAdvice(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        logger.info("Flow before advice! executing method: " + signature.toShortString());
    }

    @After("com.aspect.CRMLoggingAspect.appFlow()")
    public void afterFlowAdvice(){

    }
}
