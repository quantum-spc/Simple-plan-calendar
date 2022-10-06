package org.simple_plan_calendar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.simple_plan_calendar.entity.Calendar;
import org.simple_plan_calendar.entity.User;
import org.simple_plan_calendar.repository.CalendarRepository;
import org.simple_plan_calendar.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Long loginUser(User user) {
        log.info(user);

        User findUser = userRepository.findByMemberid(user.getMemberid());

        Long result = 0L;
        if (findUser != null) {
            if (passwordEncoder.matches(user.getMemberpw(), findUser.getMemberpw())) {
                result = findUser.getId();
            }
        }

        return result;
    }

    @Override
    public List<Calendar> getCalendarList(User user) {
        List<Calendar> calendarList = null;
        if (user != null) {
            calendarList = calendarRepository.findAllByUser(User.builder().id(user.getId()).build());

        }

        return calendarList;
    }
}
