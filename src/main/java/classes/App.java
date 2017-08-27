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


/*<context:annotation-config/>
<context:component-scan base-package="classes"/>*/

    public static void main(String[] args) {
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.scan("classes");
        context.refresh();

        App app = (App) context.getBean("app");
        String msg = "Some event for 1";
        // String message= msg.replaceAll(app.client.getId()+"", app.client.getFullName());
        Event event = (Event) context.getBean("event");
        event.setMsg(msg);
        app.logEvent(event, EventType.ERROR);

        String msg2 = "Some event for 2";
        //String message2= msg2.replaceAll(app.client.getId()+"", app.client.getFullName());
        Event event2 = (Event) context.getBean("event");
        event2.setMsg(msg2);
        app.logEvent(event2, EventType.INFO);

        context.close();

        //App app = new App();
        //app.client = new Client(1,"John Derry");
        //app.logEvent = new ConsoleEventLogger();
        // app.logEvent("Some event for user 1");
    }
}
