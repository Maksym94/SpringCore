package config;

import classes.App;
import classes.Client;
import classes.Event;
import classes.EventType;
import interfaces.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Import(LoggerConfig.class)
public class AppConfig {

    @Autowired
    private EventLogger combinedEventLogger;

    @Autowired
    private EventLogger consoleEventLogger;

    @Autowired
    private EventLogger cacheFileEventLogger;

    @Autowired
    private Client client;


    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("client.properties"));
        configurer.setIgnoreResourceNotFound(true);
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        return configurer;
    }

    @Bean
    public App app(){
        Map<EventType, EventLogger> logMap = new HashMap<>();
        logMap.put(EventType.INFO,consoleEventLogger);
        logMap.put(EventType.ERROR,combinedEventLogger);
        App app = new App(cacheFileEventLogger,client,logMap);
        return app;
    }

    @Bean
    @Scope("prototype")
    public Event event(){
        Event event = new Event(new Date(), DateFormat.getDateTimeInstance());
        return event;
    }

}