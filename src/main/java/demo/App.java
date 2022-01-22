package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/1/22 10:00
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {

        final ConfigurableApplicationContext context = SpringApplication.run(App.class);

    }
}