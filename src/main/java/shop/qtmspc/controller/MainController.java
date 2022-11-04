package shop.qtmspc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.qtmspc.WebConfig;
import shop.qtmspc.entity.User;
import shop.qtmspc.service.CalendarService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/calendar/")
@RequiredArgsConstructor
public class MainController {

    private final CalendarService calendarService;

    /**
     * 메인 페이지
     */
    @GetMapping("/index")
    public void index(Model model, @SessionAttribute(name = WebConfig.LOGIN_USER, required = false) User loginUser){
        //log.info("index page");
        //log.info(loginUser);

        model.addAttribute(WebConfig.LOGIN_USER, loginUser);
    }

    /**
     * 유저 등록
     */
    @PostMapping("/user/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes, HttpSession session){

        Long userId = calendarService.loginUser(user);

        if (userId == 0) {
            //새로 추가된 유저의 번호
            Long id = calendarService.registerUser(user);
            log.info("id : " + id);
            user.setId(id);
            session.setAttribute(WebConfig.LOGIN_USER, user);
        } else {
            redirectAttributes.addFlashAttribute("msg", "이미 존재하는 계정입니다. 다른 아이디로 등록하세요.");
        }



        return "redirect:/calendar/index";
    }

    /**
     * 유저 로그인
     */
    @PostMapping("/user/login")
    public String loginUser(User user, RedirectAttributes redirectAttributes, HttpSession session){

        Long userId = calendarService.loginUser(user);

        if (userId == 0L) {
            log.info("로그인 실패");
            redirectAttributes.addFlashAttribute("msg", "로그인에 실패했습니다.");
        } else {
            log.info("로그인 성공");
            user.setId(userId);
            session.setAttribute(WebConfig.LOGIN_USER, user);
        }

        return "redirect:/calendar/index";
    }

    /**
     * 유저 로그아웃
     */
    @PostMapping("/user/logout")
    public String logoutUser(RedirectAttributes redirectAttributes, HttpSession session){
        session.invalidate();
        //redirectAttributes.addFlashAttribute("msg", "로그아웃에 성공했습니다.");

        return "redirect:/calendar/index";
    }
}
