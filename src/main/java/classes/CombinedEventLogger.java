package classes;

import interfaces.EventLogger;

import java.util.List;

public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (int i = 0; i <loggers.size() ; i++) {
            loggers.get(i).logEvent(event);
        }

    }
}
