package shop.qtmspc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.qtmspc.mapper.UserMapper;
import shop.qtmspc.repository.CalendarRepository;
import shop.qtmspc.repository.UserRepository;
import shop.qtmspc.service.CalendarService;
import shop.qtmspc.service.CalendarServiceImplForMybatis;

public class ServiceConfigForMybatis {

    @Bean
    public CalendarService calendarService(UserRepository userRepository, CalendarRepository calendarRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        return new CalendarServiceImplForMybatis(userRepository, calendarRepository, userMapper, passwordEncoder);
    }

}
