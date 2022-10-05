package org.simple_plan_calendar.service;

import org.simple_plan_calendar.entity.User;

public interface CalendarService {

    Long registerUser(User user);

    boolean loginUser(User user);


}
