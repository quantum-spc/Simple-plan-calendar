package shop.qtmspc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.qtmspc.WebConfig;
import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.User;
import shop.qtmspc.service.CalendarService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/calendar/plan/")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * 일정 목록
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Calendar>> calendarList(@SessionAttribute(name = WebConfig.LOGIN_USER, required = false) User loginUser) {
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
            @SessionAttribute(name = WebConfig.LOGIN_USER, required = false) User loginUser
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
            @SessionAttribute(name = WebConfig.LOGIN_USER, required = false) User loginUser
            , @PathVariable(name = "id") Long id
            , @RequestBody Calendar calendar
    ){
        //log.info(calendar);
        calendar.setId(id);
        calendarService.calendarUpdate(loginUser, calendar);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
