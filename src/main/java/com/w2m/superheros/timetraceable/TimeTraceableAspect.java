package com.w2m.superheros.timetraceable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
@Aspect
@Component
public class TimeTraceableAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeTraceableAspect.class);
 
    @Around("@annotation(TimeTraceable)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        long initialTimeMillis =  System.currentTimeMillis();
        String shortSignature = joinPoint.getSignature().toShortString();
        StringBuilder args = new StringBuilder();
        for(Object arg : joinPoint.getArgs())
        	args.append(arg!=null?new StringBuilder(arg.toString()).append(","):"");
        Object result = null;
        try {
        	result = joinPoint.proceed();
        } finally {
            logger.info("Elapsed time for {} : {} milliseconds",
            		shortSignature.replace("..", args), System.currentTimeMillis() - initialTimeMillis);
		}
        return result;
    }
 
}