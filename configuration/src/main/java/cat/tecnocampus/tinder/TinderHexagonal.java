package cat.tecnocampus.tinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cat.tecnocampus.tinder.application", "cat.tecnocampus.tinder.persistenceAdapter", "cat.tecnocampus.tinder.webAdapter"
        , "cat.tecnocampus.tinder.configuration.security"})
public class TinderHexagonal {
    public static void main(String[] args) {
        SpringApplication.run(TinderHexagonal.class, args);
    }
}

