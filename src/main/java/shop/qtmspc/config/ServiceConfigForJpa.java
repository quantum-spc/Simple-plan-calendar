package shop.qtmspc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.qtmspc.repository.CalendarRepository;
import shop.qtmspc.repository.UserRepository;
import shop.qtmspc.service.CalendarService;
import shop.qtmspc.service.CalendarServiceImplForJpa;

public class ServiceConfigForJpa {

    @Bean
    public CalendarService calendarService(UserRepository userRepository, CalendarRepository calendarRepository, PasswordEncoder passwordEncoder) {
        return new CalendarServiceImplForJpa(userRepository, calendarRepository, passwordEncoder);
    }

}
