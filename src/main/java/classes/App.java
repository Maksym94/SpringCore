package classes;

import config.AppConfig;
import interfaces.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by Maksym_Petrenko on 8/17/2017.
 */
public class App {

    private EventLogger eventLogger;
    private Client client;
    private Map<EventType, EventLogger> loggers;

    public App(EventLogger eventLogger, Client client, Map<EventType, EventLogger> loggers) {
        System.out.println(client.getGreeting());
        this.eventLogger = eventLogger;
        this.client = client;
        this.loggers = loggers;
    }

    private void logEvent(Event event, EventType type) {
        String message = event.getMsg().replaceAll(client.getId() + "", client.getFullName());
        event.setMsg(message);
        EventLogger logger = loggers.get(type);
        if (type == null) {
            logger = eventLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        App app = (App) context.getBean("app");
        String msg = "Some event for 1";
        Event event = (Event) context.getBean("event");
        event.setMsg(msg);
        app.logEvent(event, EventType.ERROR);

        String msg2 = "Some event for 2";

        Event event2 = (Event) context.getBean("event");
        event2.setMsg(msg2);
        app.logEvent(event2, EventType.INFO);

        context.close();
    }
}
