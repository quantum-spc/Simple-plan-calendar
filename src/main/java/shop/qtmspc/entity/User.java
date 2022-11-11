package shop.qtmspc.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor // 올 아규먼츠 컨스트럭터 -> 파라미터가 있는 기본 생성자만 만들어 줌
@NoArgsConstructor // 노 아규먼츠 컨스트럭터 -> 파라미터가 없는 기본 생성자만 만들어 줌
@Getter
@Setter
@ToString
@DynamicInsert
@Table(name = "plan_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @ColumnDefault("'N'")
    private String delflag; // 삭제 여부

    private String memberid; // id

    private String memberpw; // 비밀번호

    private LocalDateTime lastLoginDate; // 마지막 로그인 날짜

}
