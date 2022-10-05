package org.simple_plan_calendar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simple_plan_calendar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("비밀번호 암호화 테스트")
    void paswordEncode() {
        // given
        String rawPassword = "testPassword123!@#";

        // when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // then
        assertThat(rawPassword).isNotEqualTo(encodedPassword);
        assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
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
            User user = userRepository.findByMemberid(modifyUserTestId.getMemberid());
            userRepository.save(user);

            // then
            assertThat(modifyUserTestId.getId()).isEqualTo(user.getId());
        } finally { // Exception 터지더라도 테스트로 생성한 계정은 삭제
            userRepository.delete(modifyUserTestId);
        }

    }

    @Test
    @DisplayName("유저 비밀번호 검증 테스트")
    public void checkUserPassword() {

        // given
        User user = userRepository.findByMemberid("test1");

        // then
        assertThat(passwordEncoder.matches("test1", user.getMemberpw())).isTrue();

    }

}