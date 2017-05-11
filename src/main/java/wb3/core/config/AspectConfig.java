package wb3.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wb3.core.aspect.ControllerExceptionHandlingAspect;
import wb3.core.aspect.ExceptionLoggingAspect;
import wb3.core.aspect.TraceLoggingAspect;

/**
 * Created by wbensing on 3/12/2017.
 */
@Configuration
public class AspectConfig {

    @Bean
    ControllerExceptionHandlingAspect controllerExceptionHandlingAspect() { return new ControllerExceptionHandlingAspect(); }

    @Bean
    ExceptionLoggingAspect exceptionLoggingAspect() {return new ExceptionLoggingAspect(); }

    @Bean
    TraceLoggingAspect traceLoggingAspect() {return new TraceLoggingAspect(); }
}
