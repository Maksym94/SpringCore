package classes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Maksym_Petrenko on 8/17/2017.
 */
@Component("client")
public class Client {
    private int id;
    private String fullName;

    @Value("${greeting}")
    private String greeting;

    public Client(
            @Value("${id}")
                    int id,
            @Value("${name}")
                    String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
