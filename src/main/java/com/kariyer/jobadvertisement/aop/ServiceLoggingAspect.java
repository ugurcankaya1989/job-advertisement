package com.kariyer.jobadvertisement.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Aspect
@Configuration
public class ServiceLoggingAspect {
    private final Logger LOGGER = LogManager.getLogger(ServiceLoggingAspect.class);

    @Pointcut("execution(* com.kariyer.jobadvertisement.service.impl..*(..))")
    public void serviceLayerPointCut() {}


    @Around("serviceLayerPointCut()")
    public Object serviceLayerLogging(ProceedingJoinPoint pjp){
        Date start = new Date();
        Object returnObject = null;
        try {
            returnObject = pjp.proceed();
        } catch (Throwable throwable) {
           LOGGER.error("Service Name : " + pjp.getClass() + "Error Message :" + throwable.getMessage());
        }
        Date end = new Date();
        LOGGER.info("Service Name : " + pjp.getSourceLocation().getWithinType().getName() +" Method Name : " + pjp.getSignature().getName() +" Execution Time : " + (end.getTime() - start.getTime()));
        return returnObject;
    }
}
