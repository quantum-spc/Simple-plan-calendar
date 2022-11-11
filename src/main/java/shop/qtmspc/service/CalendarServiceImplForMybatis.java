package shop.qtmspc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.User;
import shop.qtmspc.mapper.UserMapper;
import shop.qtmspc.repository.CalendarRepository;
import shop.qtmspc.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarServiceImplForMybatis implements CalendarService {

    private final UserRepository userRepository; // jpa
    private final CalendarRepository calendarRepository; // jpa

    private final UserMapper userMapper; // MyBatis. Mapper

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private SqlSession mybatisSqlSession;

    @Override
    public Long registerUser(User user) {
        //log.info(user);

        User result = User.builder()
                .memberid(user.getMemberid())
                .memberpw(passwordEncoder.encode(user.getMemberpw()))
                .build();
        userRepository.save(result);

        return result.getId();
    }

    @Override
    @Transactional
    public Long loginUser(User user) {
        //log.info(user);

        List<User> findUser = userMapper.findByMemberid(user);
        log.info("findUser : {}", findUser.toString());

        Long result = 0L;
        for(int i=0; i<findUser.size(); i++) {
            if (passwordEncoder.matches(user.getMemberpw(), findUser.get(i).getMemberpw())) {
                result = findUser.get(i).getId();
                userMapper.updateUserLastLoginDate(user);
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
    @Transactional
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
    @Transactional
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
