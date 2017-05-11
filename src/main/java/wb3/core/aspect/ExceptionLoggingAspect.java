package wb3.core.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
public class ExceptionLoggingAspect {

    Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

    @AfterThrowing(
            pointcut =  "wb3.core.config.SystemArchitecture.Repository() || wb3.core.config.SystemArchitecture.Service()",
            throwing = "ex")
    public void logException(Exception ex){
        logger.error("Exception", ex);
    }
}
