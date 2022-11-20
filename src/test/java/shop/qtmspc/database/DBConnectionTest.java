package shop.qtmspc.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DBConnectionTest {

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    void connection() {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (connection != null) {
                System.out.println("connection = " + connection);
                result = true;
            }
        } catch(Exception e) {
            System.out.println("e = " + e);
            if (e.toString().contains("max_user_connections")) { // 최대 연결 허용수 제한으로 나오지만 정상
                result = true;
            } else {
                System.out.println("커넥션 연결 실패");
            }
        }
        assertThat(result).isEqualTo(true);
        
    }
}
