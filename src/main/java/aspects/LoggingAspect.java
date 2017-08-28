package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by Maksym_Petrenko on 8/28/2017.
 */
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods(){

    }

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers(){

    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("BEFORE : "+ joinPoint.getTarget().getClass().getSimpleName()+" "
                +joinPoint.getSignature().getName());

    }

    @AfterReturning(pointcut = "allLogEventMethods()", returning="retVal" )
    public void logAfter(Object retVal){
        System.out.println("Returned value : "+ retVal);
    }

    @AfterThrowing(pointcut="allLogEventMethods()", throwing="e")
    public void logAfterThrow(Throwable e){
        System.out.println("Thrown: "+e);
    }
}
