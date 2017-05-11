package wb3.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
public class TraceLoggingAspect {

    Logger logger = LoggerFactory.getLogger(TraceLoggingAspect.class);

    @Around("wb3.core.config.SystemArchitecture.Repository() || wb3.core.config.SystemArchitecture.Service() || wb3.core.config.SystemArchitecture.Controller()")
    public Object trace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        String methodInfo = proceedingJoinPoint.getStaticPart().getSignature().toShortString();
        Object args[] = proceedingJoinPoint.getArgs();

        logPreExecution(methodInfo, args);

        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable ex){
            throw ex;
        } finally {
            logger.trace("Exiting " + methodInfo);
        }
    }

    private void logPreExecution(String methodInfo, Object[] args){
        logger.trace("Entering " + methodInfo + " | " + args.length + " arguments supplied.");
        for (Object arg : args ){
            logger.trace("Arg : " + arg.toString());
        }
    }
}