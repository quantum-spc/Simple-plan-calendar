package org.simple_plan_calendar.service;

import org.simple_plan_calendar.entity.Calendar;
import org.simple_plan_calendar.entity.User;

import java.util.List;

public interface CalendarService {

    Long registerUser(User user);

    Long loginUser(User user);

    List<Calendar> getCalendarList(User user);


}
