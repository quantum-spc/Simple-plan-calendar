package org.simple_plan_calendar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simple_plan_calendar.entity.Calendar;
import org.simple_plan_calendar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class CalendarRepositoryTest {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("캘린더 등록 테스트")
    public void insertCalendar() {

        User user = User.builder().memberid("userTest").build();
        userRepository.save(user);

        IntStream.rangeClosed(1,3).forEach(i -> {
            Calendar calendar = Calendar.builder()
                    .user(user)
                    .title("Title..."+i)
                    .start_date(LocalDateTime.now())
                    .bg_color("black")
                    .build();

            calendarRepository.save(calendar);
        });

    }

    @Test
    @DisplayName("캘린더 목록")
    public void testListByCalendar() {
        List<Calendar> calendarList = calendarRepository.findAllByUser(User.builder().id(1L).build());

        calendarList.forEach(
                calendar -> System.out.println(calendar)
        );

    }

}