package shop.qtmspc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.qtmspc.repository.CalendarRepository;
import shop.qtmspc.repository.UserRepositoryForNativeSql;
import shop.qtmspc.service.CalendarService;
import shop.qtmspc.service.CalendarServiceImplForNativeSql;

public class ServiceConfigForNativeSql {

    @Bean
    public CalendarService calendarService(UserRepositoryForNativeSql userRepository, CalendarRepository calendarRepository, PasswordEncoder passwordEncoder) {
        return new CalendarServiceImplForNativeSql(userRepository, calendarRepository, passwordEncoder);
    }

}
