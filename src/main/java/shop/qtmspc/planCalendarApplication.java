package shop.qtmspc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import shop.qtmspc.config.ServiceConfigForMybatis;


@EnableJpaAuditing
@SpringBootApplication
@Slf4j
//@Import(ServiceConfigForJpa.class)
//@Import(ServiceConfigForJpql.class)
//@Import(ServiceConfigForNativeSql.class)
@Import(ServiceConfigForMybatis.class)
public class planCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(planCalendarApplication.class, args);
    }

    @Bean
    @Profile("local")
    public ProfileLocalInit localProfileInit() {
        return new ProfileLocalInit();
    }

    @Bean
    @Profile("prod")
    public ProfileProdInit profileProdInit() {
        return new ProfileProdInit();
    }


    /**
     * 애플리케이션 중지 이벤트
     */
    @EventListener(ContextClosedEvent.class)
    public void onContextClosedEvent(ContextClosedEvent event) {
        log.info("★★★★★★★★★★★★ Project Close ★★★★★★★★★★★★");
    }

}
