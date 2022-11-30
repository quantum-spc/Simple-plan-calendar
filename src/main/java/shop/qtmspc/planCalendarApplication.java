package shop.qtmspc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import shop.qtmspc.config.ServiceConfigForMybatis;

@SpringBootApplication
@EnableJpaAuditing
//@Import(ServiceConfigForJpa.class)
//@Import(ServiceConfigForJpql.class)
//@Import(ServiceConfigForNativeSql.class)
@Import(ServiceConfigForMybatis.class)
public class planCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(planCalendarApplication.class, args);
    }

}
