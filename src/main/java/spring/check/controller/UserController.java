package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.check.cache.ScheduleCache;
import spring.check.user.dto.Members;
import spring.check.user.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private  final UserServiceImpl userService;
    private final ScheduleCache scheduleCache;

    @PostMapping("/signUp")
    public String userSignUp(@ModelAttribute Members members, Model model, HttpSession session){
        int userNum = userService.signUp(members);
        if(userNum == 1){
            Members signInMember = userService.signIn(members);
            session.setAttribute("userMail" , signInMember.getUserMail());
            session.setAttribute("userNum" , signInMember.getUserNum());
            if(signInMember.getUserImg() != null){
                session.setAttribute("userImg", Base64.getEncoder().encodeToString(signInMember.getUserImg()));
            }
            return "redirect:/check/view?date=" +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }else{
            return "redirect:?status=join_fail";
        }
    }

    @GetMapping("/signUp")
    public String signUp(){
        return "sign_up";
    }

    @PostMapping
    public String userSignIn(@ModelAttribute Members members , HttpSession session){
        log.info("UserControllerUserSignIn");
        Members signInMember = userService.signIn(members);

        if(signInMember != null){
            session.setAttribute("userMail" , signInMember.getUserMail());
            session.setAttribute("userNum" , signInMember.getUserNum());
            if(signInMember.getUserImg() != null){
                session.setAttribute("userImg", Base64.getEncoder().encodeToString(signInMember.getUserImg()));
            }
            return "redirect:/check/view?date=" +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }else {
            return "redirect:?status=fail";
        }
    }

    @GetMapping
    public String backPage(HttpSession session){ // 뒤로가기 방지
        return (session.getAttribute("userMail") != null && session.getAttribute("userNum") != null)? "check/main" : "index";
    }

    @GetMapping("/findPass")
    public String findPass(){
        return "findPass";
    }

    @PostMapping("/findPass")
    public String editPass(@ModelAttribute Members members){
        return (userService.editPass(members) == 1) ? "redirect:/?status=pass" : "index";
    }

    @GetMapping("/logout")
    public String userLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        scheduleCache.infoScheduleCacheAllDelete();
        return "index";
    }

    @ResponseBody
    @PostMapping("/img")
    public String userImgEdit(@RequestParam MultipartFile resizedImage, HttpSession session) throws IOException {
        int userNum = (int) session.getAttribute("userNum");
        if(userService.editImg(userNum, resizedImage) > 0 ) {
            session.setAttribute("userImg", Base64.getEncoder().encodeToString(resizedImage.getBytes()));
            return "ok";
        }else return "";
    }

    @ResponseBody
    @GetMapping("/{userMail}")
    public int findId(@PathVariable String userMail){
        log.info(userMail);
        return userService.findId(userMail);
    }

}
