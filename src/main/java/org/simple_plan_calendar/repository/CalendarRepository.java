package org.simple_plan_calendar.repository;

import org.simple_plan_calendar.entity.Calendar;
import org.simple_plan_calendar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByUserAndDelflag(User user, String delflag);
}
