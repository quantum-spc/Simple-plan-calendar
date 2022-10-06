package org.simple_plan_calendar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.simple_plan_calendar.entity.Calendar;
import org.simple_plan_calendar.entity.User;
import org.simple_plan_calendar.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequestMapping("/calendar/plan/")
@Log4j2
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Calendar>> getCalendarList(@SessionAttribute(name = "loginUser", required = false) User loginUser) {
        log.info("loginUser ::: " + loginUser);
        List<Calendar> calendarList = calendarService.getCalendarList(loginUser);
        log.info("calendarList ::: " + calendarList);

        return new ResponseEntity<>( calendarList, HttpStatus.OK);
    }
}
