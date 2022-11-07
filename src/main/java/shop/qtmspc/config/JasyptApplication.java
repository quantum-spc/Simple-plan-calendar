package shop.qtmspc.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

/**
 * 암호화 텍스트 생성 APP
 */
//@SpringBootApplication
public class JasyptApplication implements CommandLineRunner {

    @Value("${jasypt.encryptor.password}")
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