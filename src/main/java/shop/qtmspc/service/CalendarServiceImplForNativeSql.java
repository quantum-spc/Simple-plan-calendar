package shop.qtmspc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.User;
import shop.qtmspc.repository.CalendarRepository;
import shop.qtmspc.repository.UserRepositoryForNativeSql;

import java.util.List;

//@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarServiceImplForNativeSql implements CalendarService {

    private final UserRepositoryForNativeSql userRepositoryForNativeSql;
    private final CalendarRepository calendarRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long registerUser(User user) {
        //log.info(user);

        User result = User.builder()
                .memberid(user.getMemberid())
                .memberpw(passwordEncoder.encode(user.getMemberpw()))
                .build();
        userRepositoryForNativeSql.save(result);

        return result.getId();
    }

    @Override
    public Long loginUser(User user) {
        //log.info(user);

        List<User> findUser = userRepositoryForNativeSql.findByMemberid(user.getMemberid());

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
