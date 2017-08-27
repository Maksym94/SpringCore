package config;

import classes.CacheFileEventLogger;
import classes.CombinedEventLogger;
import classes.ConsoleEventLogger;
import classes.FileEventLogger;
import interfaces.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoggerConfig {

    @Autowired
    private EventLogger fileEventLogger;

    @Autowired
    private EventLogger cacheFileEventLogger;

    @Autowired
    private EventLogger consoleEventLogger;

    @Bean
    public ConsoleEventLogger consoleEventLogger() {

        return new ConsoleEventLogger();
    }

    @Bean
    public EventLogger fileEventLogger() {
        EventLogger fileEventLogger = new FileEventLogger("fileLogger.txt");
        return fileEventLogger;
    }

    @Bean
    public EventLogger cacheFileEventLogger() {
        EventLogger cacheFileEventLogger = new CacheFileEventLogger("fileLogger.txt", 2);
        return cacheFileEventLogger;
    }

    @Bean
    public EventLogger combinedEventLogger() {
        List<EventLogger> loggers = new ArrayList<>();
        loggers.add(fileEventLogger);
        loggers.add(consoleEventLogger);
        EventLogger combinedEventLogger = new CombinedEventLogger(loggers);
        return combinedEventLogger;
    }

}