package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.check.user.Members;
import spring.check.user.UserServiceImpl;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    @PostMapping
    public String userSignUp(@ModelAttribute Members members){
        return (userService.signUp(members) != null) ? "성공" : "실패";
    }

    @PostMapping("/signIn")
    public String userSignIn(@ModelAttribute Members members , HttpSession session) {
        // 해당 로직을 서비스에 구현 할 지 컨트롤러에서 구현 할 지 미정
        if(userService.signIn(members) == 1){
            // 회원 정보 어떤식으로 저장 할 지 선택
            session.setAttribute("userNum" , members.getUserNum());
            return "";
        }else return "실패";
    }
    
    @PatchMapping
    public String userEdit(@ModelAttribute Members members){
        return (userService.editImg(members) == 1) ? "성공" : "실패";
    }

    @PostMapping("/{userId}")
    public String userEditPass(@ModelAttribute Members members){
        return (userService.editPass(members) == 1) ? "성공" : "실패";
    }

    @ResponseBody
    public String findId(@RequestParam String userId){
        return (userService.findId(userId) != null) ? "true" : "false";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session){
        session.getClass();
        return "";
    }

}
