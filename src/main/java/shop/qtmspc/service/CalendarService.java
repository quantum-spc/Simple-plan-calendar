package shop.qtmspc.service;

import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.User;

import java.util.List;

public interface CalendarService {

    Long registerUser(User user);

    Long loginUser(User user);

    List<Calendar> calendarList(User user);

    void calendarInsert(User loginUser, Calendar calendar);

    void calendarUpdate(User loginUser, Calendar calendar);


}
