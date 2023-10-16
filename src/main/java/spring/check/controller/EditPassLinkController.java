package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.check.editPassLink.dto.EditPasswordLinkInfo;
import spring.check.editPassLink.service.EditPassLinkServiceImpl;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/edit")
public class EditPassLinkController {

    private final EditPassLinkServiceImpl emailService;
    @PostMapping
    @ResponseBody
    public String sendEmail(@RequestParam String userMail){ // 전에 보낸 링크 데이터는 자동 삭제
        return emailService.email_sand_info(userMail);
    }

    @GetMapping("/{random_id}")
    public String linkOpen(@PathVariable String random_id, @RequestParam int userNum, Model model){
        EditPasswordLinkInfo id = emailService.checkExpirationDate(random_id, userNum);
        if(id != null){
            model.addAttribute("userNum", id.getUserNum());
            return "editPass";
        }else {
            return "redirect:/?status=link";
        }
    }
}
