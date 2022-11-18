package shop.qtmspc.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.QCalendar;
import shop.qtmspc.entity.User;

import java.time.LocalDate;
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

        User user = User.builder().memberid("test1").build();
        userRepository.save(user);

        IntStream.rangeClosed(1,3).forEach(i -> {
            Calendar calendar = Calendar.builder()
                    .user(user)
                    .title("Title..."+i)
                    .start(LocalDate.now())
                    .color("black")
                    .build();

            calendarRepository.save(calendar);
        });

    }

    @Test
    @DisplayName("캘린더 목록")
    public void testListByCalendar() {
        List<Calendar> calendarList = calendarRepository.findAllByUserAndDelflag(User.builder().id(1L).build(), "N");

        calendarList.forEach(
                calendar -> System.out.println(calendar.toString())
        );

    }

    @Test
    @DisplayName("QueryDSL 테스트")
    public void SelectUserById() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        QCalendar qCalendar = QCalendar.calendar;

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qCalendar.title.contains("TEST");
        builder.and(expression);

        Page<Calendar> result = calendarRepository.findAll(builder, pageable);

        result.stream().forEach(calendar -> {
            System.out.println(calendar.toString());
        });
    }

}