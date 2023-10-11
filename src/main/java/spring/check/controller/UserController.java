package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.check.user.dto.Members;
import spring.check.user.service.UserServiceImpl;
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

    @PostMapping("/signUp")
    public String userSignUp(@ModelAttribute Members members, Model model, HttpSession session){
        int userNum = userService.signUp(members);
        if(userNum == 1){
            Members signInMember = userService.signIn(members);
            session.setAttribute("userMail" , signInMember.getUserMail());
            session.setAttribute("userNum" , signInMember.getUserNum());
            model.addAttribute("status", "success");
            return "redirect:http://localhost:8080/check?date=" +
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
            return "redirect:http://localhost:8080/check?date=" +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }else {
            return "redirect:?status=fail";
        }
    }

    @GetMapping
    public String backPage(HttpSession session){ // 회원가입 후 뒤로가기 방지
        return (session.getAttribute("userMail") != null && session.getAttribute("userNum") != null)? "check/main" : "index";
    }

    @GetMapping("/findPass")
    public String findPass(){
        return "findPass";
    }

    @PostMapping("/findPass")
    public String editPass(@ModelAttribute Members members){
        return (userService.editPass(members) == 1) ? "redirect:http://localhost:8080/?status=pass" : "index";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session){
        session.removeAttribute("userMail");
        session.removeAttribute("userNum");
        return "index";
    }

    @ResponseBody
    @PostMapping("/img")
    public String userImgEdit(@RequestParam MultipartFile resizedImage, HttpSession session) throws IOException {
        int userNum = (int) session.getAttribute("userNum");
        if(userService.editImg(userNum, resizedImage) > 0 ) {
            session.setAttribute("userImg", resizedImage.getBytes());
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
