package wb3.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TracingAspect {

    private static final String _pointCut = "execution(* *(..))";

    // Before the method is executed
    @Before(_pointCut)
    public void entering(JoinPoint joinPoint){
        logJoinPointInformation("entering", joinPoint);
    }

    // Wraps the original method called, can prevent original method from being called, and can catch exceptions, can modify return values
    // This is generally necessary when complex cross-cutting logic is involved, otherwise use the @Before and @After
    @Around(_pointCut)
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logProceedingJoinPointInformation("Entering", proceedingJoinPoint);
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable ex){
            logException(proceedingJoinPoint, ex);
            throw ex;
        } finally {
            logProceedingJoinPointInformation("Exiting", proceedingJoinPoint);
        }
    }

    // After the original method has been executed
    @After(_pointCut)
    public void exiting(JoinPoint joinPoint){
        logJoinPointInformation("exiting", joinPoint)
                .logArguments(joinPoint);
    }

    // Executed after the method throws an exception
    @AfterThrowing(pointcut = _pointCut, throwing = "ex")
    public void afterThrowing(RuntimeException ex){
        logException(ex);
    }

    // Executed if the method returned successfully
    @AfterReturning(pointcut = _pointCut, returning = "string")
    public void afterReturningString(String string){
        logMethodString("result", string);
    }

    private TracingAspect logProceedingJoinPointInformation(String resultType, ProceedingJoinPoint proceedingJoinPoint){
        System.out.println(
                resultType
                        + " "
                        + getMethodName(proceedingJoinPoint)
        );
        return this;
    }

    private TracingAspect logJoinPointInformation(String resultType, JoinPoint joinPoint){
        System.out.println(
                resultType
                + " "
                + getMethodName(joinPoint)
        );
        return this;
    }

    private TracingAspect logArguments(JoinPoint joinPoint){
        for (Object arg : joinPoint.getArgs()){
            System.out.println("Arg : " + arg);
        }
        return this;
    }

    private TracingAspect logException(RuntimeException ex){
        System.out.println("Exception " + ex.toString());
        return this;
    }

    private TracingAspect logException(ProceedingJoinPoint proceedingJoinPoint, Throwable ex){
        System.out.println("Exception in "
                + getMethodName(proceedingJoinPoint)
                + "/n "
                + ex.toString());
        return this;
    }

    private TracingAspect logMethodString(String resultType, String returnResult){
        System.out.println(resultType + " " + returnResult);
        return this;
    }

    private String getMethodName(ProceedingJoinPoint proceedingJoinPoint){
        return proceedingJoinPoint.getStaticPart().getSignature().toString();
    }

    private String getMethodName(JoinPoint joinPoint){
        return joinPoint.getStaticPart().getSignature().toString();
    }
}