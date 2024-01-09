package com.stocklisting.favourite.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FavStockAOP {

    private final Logger logger = LoggerFactory.getLogger(FavStockAOP.class);

    @Before("execution(* com.stocklisting.favourite.service.FavouriteStockService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().toShortString());
        System.out.println("Entering method: " + joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "execution(* com.stocklisting.favourite.service.FavouriteStockService.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: " + joinPoint.getSignature().toShortString() + " => " + exception.getMessage());
        System.out.println("Exception in method: " + joinPoint.getSignature().toShortString() + " => " + exception.getMessage());
    }
}