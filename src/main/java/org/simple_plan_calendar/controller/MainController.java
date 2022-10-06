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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/calendar/")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final CalendarService calendarService;


    @GetMapping("/index")
    public void index(PageRequestDTO pageRequestDTO, Model model, @SessionAttribute(name = "loginUser", required = false) User loginUser){
        log.info("index page");
        log.info(loginUser);

        model.addAttribute("loginUser", loginUser);
    }

    @PostMapping("/user/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes){

        //새로 추가된 유저의 번호
        Long id = calendarService.registerUser(user);

        log.info("id : " + id);

        return "redirect:/calendar/index";
    }

    @PostMapping("/user/login")
    public String loginUser(User user, RedirectAttributes redirectAttributes, HttpSession session){

        boolean result = calendarService.loginUser(user);

        if (result) {
            log.info("로그인 성공");
            session.setAttribute("loginUser", user);
        } else {
            log.info("로그인 실패");
            redirectAttributes.addFlashAttribute("msg", "로그인에 실패했습니다.");
        }

        return "redirect:/calendar/index";
    }

    @PostMapping("/user/logout")
    public String logoutUser(User user, RedirectAttributes redirectAttributes, HttpSession session){
        session.invalidate();

        return "redirect:/calendar/index";
    }
}
