package org.simple_plan_calendar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.simple_plan_calendar.entity.User;
import org.simple_plan_calendar.repository.CalendarRepository;
import org.simple_plan_calendar.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarServiceImpl implements CalendarService {

    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long registerUser(User user) {
        log.info(user);

        User result = User.builder()
                .memberid(user.getMemberid())
                .memberpw(passwordEncoder.encode(user.getMemberpw()))
                .build();
        userRepository.save(result);

        return result.getId();
    }

    @Override
    public boolean loginUser(User user) {
        log.info(user);

        User findUser = userRepository.findByMemberid(user.getMemberid());

        boolean result = false;
        if (findUser != null) {
            result = passwordEncoder.matches(user.getMemberpw(), findUser.getMemberpw());
        }

        return result;
    }
}
