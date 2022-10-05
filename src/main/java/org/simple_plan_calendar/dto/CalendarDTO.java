package org.simple_plan_calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalendarDTO {

    private Long id;

    private LocalDateTime start_date;
    private LocalDateTime end_date;

    private String title;
    private String bg_color;

}
