package org.simple_plan_calendar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.simple_plan_calendar.dto.PageRequestDTO;
import org.simple_plan_calendar.entity.User;
import org.simple_plan_calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/calendar/")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final CalendarService calendarService;


    @GetMapping("/index")
    public void index(PageRequestDTO pageRequestDTO, Model model){
        log.info("index page");
    }

    @PostMapping("/user/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes){

        //새로 추가된 유저의 번호
        Long id = calendarService.registerUser(user);

        log.info("id : " + id);

        return "redirect:/calendar/index";
    }

    @PostMapping("/user/login")
    public String loginUser(User user, RedirectAttributes redirectAttributes){

        boolean result = calendarService.loginUser(user);
        System.out.println("result = " + result);

        return "redirect:/calendar/index";
    }
}
