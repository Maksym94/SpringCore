package classes;

import aspects.LoggingAspect;
import aspects.StatisticsAspect;
import config.AppConfig;
import interfaces.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Maksym_Petrenko on 8/17/2017.
 */
public class App {

    private EventLogger eventLogger;
    private Client client;
    private Map<EventType, EventLogger> loggers;
    /*@Autowired
    private StatisticsAspect statAspect;*/

    public App(EventLogger eventLogger, Client client, Map<EventType, EventLogger> loggers) {
        this.eventLogger = eventLogger;
        this.client = client;
        this.loggers = loggers;
    }

    private void logEvent(Event event, EventType type) {
        String message = event.getMsg()
                .replaceAll(
                        client.getId() + "",
                        client.getFullName());
        event.setMsg(message);
        EventLogger logger = loggers.get(type);
        if (type == null) {
            logger = eventLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        int successExperiments = 0;
        int winnerNumber = 7;
        int numberExperiments = 1_000_000_000;
        int[][] solutionArray = new int[5][10];
        for (int k = 0; k < numberExperiments; k++) {
            for (int i = 0; i < solutionArray.length; i++) {
                for (int j = 0; j < solutionArray[i].length; j++) {
                    solutionArray[i][j] = j + 1;
                }
            }
            for (int[] arr : solutionArray) {
                shuffleArray(arr);
            }
            for (int i = 0; i < solutionArray.length - 1; i++) {
                if (solutionArray[i][1] != winnerNumber || solutionArray[i][1] != solutionArray[i + 1][1]) {
                    break;
                }
                if (solutionArray[i][1] == solutionArray[i + 1][1] && i == solutionArray.length - 2) {
                    successExperiments++;
                }
            }
        }
        double resultOfProbability = (double) successExperiments / (double) numberExperiments;
        double resultInPercents= resultOfProbability* (double) 100;
        System.out.println(resultInPercents);

    }

    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();

        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

   /* public static void main(String[] args) {
        int numberStudents = 30;
        int numberDays = 365;
        int numberExperiments = 1000000;
        int successExperiments = 0;

        Random random = new Random();

        int[] students = new int[numberStudents];
        nextExperiment:
        for (int i = 0; i < numberExperiments; i++) {
            for (int j = 0; j < students.length; j++) {
                students[j] = random.nextInt(numberDays) + 1;
            }
            for (int j = 0; j < students.length; j++) {
                for (int k = j; k < students.length - 1; k++) {
                    if (students[j] == students[k + 1]) {
                        successExperiments++;
                        continue nextExperiment;
                    }
                }
            }


        }

        double resultOfProbability = (double) successExperiments / (double) numberExperiments;
        double resultInPercents= resultOfProbability* (double) 100;
        System.out.println(resultInPercents);

        System.exit(0);




        Client clientOrigin = new Client();
        clientOrigin.setId(45);
        clientOrigin.setFullName("Maxim");
        Object client = clientOrigin;

        Class reflectClient = client.getClass();

        Field[] clientFields=reflectClient.getDeclaredFields();
        for (Field field: clientFields) {
            field.setAccessible(true);
            try {
                System.out.println("Field name "+field.getName()+"  "+ field.get(client));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

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
        StatisticsAspect statAspect= context.getBean(StatisticsAspect.class);

        for (Map.Entry<Class<?>,Integer> entry:statAspect.getCounter().entrySet()) {
            Class<?> clazz = entry.getKey();
            Integer number = entry.getValue();
            System.out.println(clazz.getSimpleName()+" "+ number);
        }

        context.close();
    }*/
}
