package shop.qtmspc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.qtmspc.repository.CalendarRepository;
import shop.qtmspc.repository.UserRepositoryForJpql;
import shop.qtmspc.service.CalendarService;
import shop.qtmspc.service.CalendarServiceImplForJpql;

public class ServiceConfigForJpql {

    @Bean
    public CalendarService calendarService(UserRepositoryForJpql userRepository, CalendarRepository calendarRepository, PasswordEncoder passwordEncoder) {
        return new CalendarServiceImplForJpql(userRepository, calendarRepository, passwordEncoder);
    }

}
