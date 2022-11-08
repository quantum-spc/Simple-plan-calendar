package shop.qtmspc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.User;
import shop.qtmspc.repository.CalendarRepository;
import shop.qtmspc.repository.UserRepositoryForJpql;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarServiceImplForJpql implements CalendarService {

    private final UserRepositoryForJpql userRepositoryForJpql;
    private final CalendarRepository calendarRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long registerUser(User user) {
        //log.info(user);

        User result = User.builder()
                .memberid(user.getMemberid())
                .memberpw(passwordEncoder.encode(user.getMemberpw()))
                .build();
        userRepositoryForJpql.save(result);

        return result.getId();
    }

    @Override
    public Long loginUser(User user) {
        //log.info(user);

        List<User> findUser = userRepositoryForJpql.findByMemberid(user.getMemberid());
        System.out.println("findUser = " + findUser);

        Long result = 0L;
        for(int i=0; i<findUser.size(); i++) {
            if (passwordEncoder.matches(user.getMemberpw(), findUser.get(i).getMemberpw())) {
                result = findUser.get(i).getId();
            }
        }

        return result;
    }

    @Override
    public List<Calendar> calendarList(User user) {
        List<Calendar> calendarList = null;
        if (user != null) {
            calendarList = calendarRepository.findAllByUserAndDelflag(User.builder().id(user.getId()).build(), "N");
        }

        return calendarList;
    }
    @Override
    public void calendarInsert(User loginUser, Calendar calendar) {
        Calendar result = Calendar.builder()
                .user(loginUser)
                .title(calendar.getTitle())
                .start(calendar.getStart())
                .color(calendar.getColor())
                .build();

        calendarRepository.save(result);
    }

    @Override
    public void calendarUpdate(User loginUser, Calendar calendar) {
        Calendar result = Calendar.builder()
                .id(calendar.getId())
                .delflag(calendar.getDelflag())
                .user(loginUser)
                .title(calendar.getTitle())
                .start(calendar.getStart())
                .end(calendar.getEnd())
                .color(calendar.getColor())
                .build();

        calendarRepository.save(result);
    }

}
