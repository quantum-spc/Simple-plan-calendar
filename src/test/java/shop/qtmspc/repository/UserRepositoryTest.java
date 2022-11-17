package shop.qtmspc.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.qtmspc.entity.User;
import shop.qtmspc.mapper.UserMapper;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 다른 데이터베이스간의 트랜잭션 테스트
    //@Autowired
    //private SqlSession mybatisSqlSession;

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("비밀번호 암호화 테스트")
    void paswordEncode() {
        // given
        String rawPassword = "testPassword123!@#";

        // when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // then
        boolean matchResult = passwordEncoder.matches(rawPassword, encodedPassword);
        Assertions.assertThat(matchResult).isTrue();
    }

    @Test
    @DisplayName("유저 등록 테스트")
    public void insertUsers() {

        IntStream.rangeClosed(1, 3).forEach(i -> {
            User user = User.builder()
                    .memberid("id" + i)
                    .memberpw(passwordEncoder.encode("pw" + i))
                    .build();
            userRepository.save(user);
        });
    }

    @Test
    @DisplayName("유저 등록 후 조회 테스트")
    public void modifyUser() {
        // given
        User modifyUserTestId = User.builder()
                .memberid("modifyUserTestId")
                .memberpw(passwordEncoder.encode("modifyUserTestPw"))
                .build();
        userRepository.save(modifyUserTestId);

        try {
            // when
            List<User> user = userRepository.findByMemberid(modifyUserTestId.getMemberid());
            userRepository.save(user.get(0));

            // then
            assertThat(modifyUserTestId.getId()).isEqualTo(user.get(0).getId());
        } finally { // Exception 터지더라도 테스트로 생성한 계정은 삭제
            userRepository.delete(modifyUserTestId);
        }

    }

    @Test
    @DisplayName("유저 비밀번호 검증 테스트")
    public void checkUserPassword() {

        // given
        List<User> user = userRepository.findByMemberid("test");

        // then
        assertThat(passwordEncoder.matches("test", user.get(0).getMemberpw())).isTrue();
    }

    @Test
    @DisplayName("mybatis select 테스트")
    public void testMybatisSelect() {
        User user = new User();
        user.setMemberid("test");
        List<User> result = userMapper.findByMemberid(user);
        log.info("result : {}", result.toString());
        assertThat(user.getMemberid()).isEqualTo(result.get(0).getMemberid());

    }

}
