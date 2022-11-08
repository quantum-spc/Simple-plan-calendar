package shop.qtmspc.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
//@EntityListeners(value = { AuditingEntityListener.class })
@Getter
@Setter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regdate; // 등록일

    @LastModifiedDate
    @Column(name ="moddate")
    private LocalDateTime modDate; // 수정일

}
