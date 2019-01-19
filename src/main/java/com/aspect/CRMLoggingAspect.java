package com.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CRMLoggingAspect {

    @Pointcut("execution(* com.controller.*.*(..))")
    public void controllerPackage() {
    }

    @Pointcut("execution(* com.service.*.*(..))")
    public void servicePackage(){
    }

    @Pointcut("execution(* com.dao.*.*(..))")
    public void daoPackage(){
    }

    @Pointcut("controllerPackage() || servicePackage() || daoPackage()")
    public void appFlow(){
    }
}
