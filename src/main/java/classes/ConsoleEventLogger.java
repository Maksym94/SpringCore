package classes;

import interfaces.EventLogger;

/**
 * Created by Maksym_Petrenko on 8/17/2017.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event){
        System.out.println(event);
    }
}
