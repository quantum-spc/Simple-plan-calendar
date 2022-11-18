package shop.qtmspc;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * 암호화 텍스트 생성 APP
 * 패스워드 생성할경우 @Value 값을 사용해야 하기 때문에 @SpringBootApplication 주석을 해제하여 비밀번호를 생성
 */
//@SpringBootApplication
@PropertySource("classpath:jasyptPassword.properties")
public class JasyptApplication implements CommandLineRunner {

    @Value("${jasypt.encryptor.password.ignore}")
    private String encryptKey;

    public static void main(String[] args) {
        SpringApplication.run(JasyptApplication.class, args);
    }

    public void run(String... args) {
        StandardPBEStringEncryptor spe = new StandardPBEStringEncryptor();
        spe.setAlgorithm("PBEWithMD5AndDES");
        spe.setPassword(encryptKey);
        System.out.println("enc result = " + spe.encrypt("password"));
    }
}