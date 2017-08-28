package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksym_Petrenko on 8/28/2017.
 */

@Aspect
public class StatisticsAspect {
    private Map<Class<?>, Integer> counter= new HashMap<>();

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        if(!counter.containsKey(clazz)){
         counter.put(clazz,0);
        }
        counter.put(clazz,counter.get(clazz)+1);
    }

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }
}
