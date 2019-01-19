package com.aspect;

import com.config.MvcConfiguration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class FlowAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("com.aspect.CRMAspect.appFlow()")
    public void beforeFlowAdvice(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        logger.info("Flow before advice! executing method: " + signature.toShortString());
    }

    @AfterReturning(pointcut ="com.aspect.CRMAspect.appFlow()", returning = "result")
    public void afterFlowAdvice(JoinPoint joinPoint, Object result){
        logger.info("Flow after returning from method: "+ joinPoint.getSignature().toShortString());
        logger.info("Result: " + result);

    }
}
