package com.example.monolith.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all controllers and services
    @Pointcut("execution(* com.example.monolith.controller..*(..)) || execution(* com.example.monolith.service..*(..))")
    public void applicationPackagePointcut() {
        // Pointcut methods have empty body
    }

    // Around advice: logs before, after, and execution time
    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Before execution
        logger.info("Entering method: {} | Args: {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs()));

        Object result;

        try {
            result = joinPoint.proceed(); // method execution
        } catch (Exception ex) {
            logger.error("Exception in method: {} | Message: {}",
                    joinPoint.getSignature().toShortString(),
                    ex.getMessage(), ex);
            throw ex;
        }

        long executionTime = System.currentTimeMillis() - startTime;

        // After execution
        logger.info("Exiting method: {} | Result: {} | Execution time: {} ms",
                joinPoint.getSignature().toShortString(),
                result,
                executionTime);

        return result;
    }
}
