package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.check.user.Members;
import spring.check.user.service.UserServiceImpl;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private  final UserServiceImpl userService;

    @PostMapping("/{userId}")
    public String userSignUp(@ModelAttribute Members members){
        return (userService.signUp(members) != 0) ? "" : "실패";
    }

    @PostMapping
    public String userSignIn(@ModelAttribute Members members , HttpSession session){
        log.info("UserControllerUserSignIn");
        Members signInMember = userService.signIn(members);

        if(signInMember != null){
            session.setAttribute("userMail" , signInMember.getUserMail());
            session.setAttribute("userNum" , signInMember.getUserNum());
            return "check/main"; // ??
        }else {
            return "redirect:?status=false";
        }
    }

    @GetMapping("/findPass")
    public String findPass(){
        return "findPass";
    }

    @PostMapping("/{userNum}/pass")
    public String editPass(@ModelAttribute Members members){
        return (userService.editPass(members) == 1) ? "성공" : "실패";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session){
        session.removeAttribute("userMail");
        session.removeAttribute("userNum");
        return "/";
    }
    
    @ResponseBody
    @PostMapping("/{userNum}/img")
    public String userImgEdit(@ModelAttribute Members members){
        return (userService.editImg(members) == 1) ? "성공" : "실패";
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public String findId(@PathVariable String userId){
        return (userService.findId(userId) != null) ? "true" : "false";
    }

}
