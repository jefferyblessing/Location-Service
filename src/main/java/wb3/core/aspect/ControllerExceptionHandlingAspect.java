package wb3.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wb3.core.exception.*;

@Aspect
public class ControllerExceptionHandlingAspect {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlingAspect.class);

    @Around("wb3.core.config.SystemArchitecture.Controller()")
    public Object trace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result = null;
        try {

            // Intercept result before it is returned from Aspect to the caller
            result =  proceedingJoinPoint.proceed();

            //  Run Controller validations against the result before returning it
            CheckForNullModel(result);

        } catch (Throwable ex) {

            //  Handle any exceptions from the Controller validation checks
            HandleNullModel(ex);
            HandleResourceNotFound(ex);
            HandleInvalidDTO(ex);

            // Contain any unhandled exceptions; Log them and tell controller to respond with a 500 Internal Server Error
            logAndExecuteServerException(proceedingJoinPoint, ex);
        }

        return result;
    }

    private void logAndExecuteServerException(ProceedingJoinPoint proceedingJoinPoint, Throwable ex){

        String methodInfo = proceedingJoinPoint.getStaticPart().getSignature().toShortString();
        Object[] args = proceedingJoinPoint.getArgs();

        // Generate Log information
        logger.error("Internal Exception (500) Generated For " + methodInfo + " | " + args.length + " arguments supplied.");
        for (Object arg : args ){
            logger.info("Arg : " + arg.toString());
        }
        logger.error("Exception Stack Trace", ex);

        // Throw error
        throw new InternalServerException();
    }

    private void CheckForNullModel(Object checkMe) throws NullModel {
        if(checkMe == null){throw new NullModel();}
    }

    private void HandleNullModel(Throwable ex)  {
        if(ex instanceof NullModel){throw new ResourceNotFound();}
    }

    private void HandleResourceNotFound(Throwable ex) {
        if(ex instanceof ResourceNotFound){ throw new ResourceNotFound();}
    }

    private void HandleInvalidDTO(Throwable ex){
        if(ex instanceof InvalidDTO){ throw new MalformedRequest(); }
    }
}