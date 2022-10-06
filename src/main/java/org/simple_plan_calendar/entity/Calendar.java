package org.simple_plan_calendar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Table(name = "plan_calendar")
    @ToString(exclude = "user")
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate start; // 시작 날짜

    private LocalDate end; // 종료 날짜

    private String title; // 제목

    private String color; // 배경 색깔

    //private String text_color;
}
