package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksym_Petrenko on 8/28/2017.
 */

@Aspect
public class StatisticsAspect {
    private Map<Class<?>, Integer> counter= new HashMap<>();
    private static final int MAX_COUNT=1;
    private static int count;

    @Pointcut("execution(* *.logEvent(*))")
    void allLogEventMethods(){

    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        if(!counter.containsKey(clazz)){
         counter.put(clazz,0);
        }
        counter.put(clazz,counter.get(clazz)+1);
        count++;
    }

    /*@Pointcut("execution(* *.logEvent(*))&amp;&amp; within(*.Console*Logger)")
    private void consoleLoggerMethods(){

    }*/

    //@Around("consoleLoggerMethods()&& args(evt)")
    //@Around("allLogEventMethods()&& args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint joinPoint, Object evt){
    if(count>MAX_COUNT){

        try {
            joinPoint.proceed(new Object[]{evt});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
    }

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }
}
