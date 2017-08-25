package classes;

import interfaces.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by Maksym_Petrenko on 8/17/2017.
 */
public class App {
    private EventLogger eventLogger;
    private Client client;

    public App(EventLogger eventLogger, Client client) {
        this.eventLogger = eventLogger;
        this.client = client;
    }

    public void eventLogger(Event event){
        String message= event.getMsg().replaceAll(client.getId()+"", client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        App app = (App) context.getBean("app");
        String msg="Some event for 1";
       // String message= msg.replaceAll(app.client.getId()+"", app.client.getFullName());
        Event event= (Event) context.getBean("event");
        event.setMsg(msg);
        app.eventLogger(event);

        String msg2="Some event for 2";
        //String message2= msg2.replaceAll(app.client.getId()+"", app.client.getFullName());
        Event event2= (Event) context.getBean("event");
        event2.setMsg(msg2);
        app.eventLogger(event2);

        context.close();

        //App app = new App();
        //app.client = new Client(1,"John Derry");
        //app.eventLogger = new ConsoleEventLogger();
       // app.eventLogger("Some event for user 1");
    }
}
