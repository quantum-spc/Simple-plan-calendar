package shop.qtmspc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class planCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(planCalendarApplication.class, args);
    }

}
