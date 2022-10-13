package org.simple_plan_calendar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.simple_plan_calendar.entity.Calendar;
import org.simple_plan_calendar.entity.User;
import org.simple_plan_calendar.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar/plan/")
@Log4j2
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * 일정 목록
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Calendar>> calendarList(@SessionAttribute(name = "loginUser", required = false) User loginUser) {
        //log.info("loginUser ::: " + loginUser);
        List<Calendar> calendarList = calendarService.calendarList(loginUser);
        //log.info("calendarList ::: " + calendarList);

        return new ResponseEntity<>( calendarList, HttpStatus.OK);
    }

    /**
     * 일정 등록
     * @param calendar 일정
     */
    @PostMapping(value = "/")
    public ResponseEntity<Long> calendarInsert(
            @SessionAttribute(name = "loginUser", required = false) User loginUser
            , @RequestBody Calendar calendar
        ){
        //log.info(calendar);

        calendarService.calendarInsert(loginUser, calendar);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 일정 업데이트
     * @param calendar 일정
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> calendarUpdate(
            @SessionAttribute(name = "loginUser", required = false) User loginUser
            , @PathVariable(name = "id") Long id
            , @RequestBody Calendar calendar
    ){
        //log.info(calendar);
        calendar.setId(id);
        calendarService.calendarUpdate(loginUser, calendar);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
