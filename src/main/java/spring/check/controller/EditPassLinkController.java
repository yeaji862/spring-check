package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.check.editPassLink.EditPasswordLinkInfo;
import spring.check.editPassLink.CreateRandomId;
import spring.check.editPassLink.service.EditPassLinkServiceImpl;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/edit")
public class EditPassLinkController {

    private final EditPassLinkServiceImpl emailService;
    @GetMapping
    @ResponseBody
    public String sendEmail(@RequestParam String userMail, @RequestParam int userNum){
        CreateRandomId createRandomId = new CreateRandomId();
        String randomId = createRandomId.createRandomId();
        emailService.email_sand_info(randomId, userMail, userNum);
        return "ok";
    }

    @GetMapping("/{random_id}")
    public String linkOpen(@PathVariable String random_id, @RequestParam int userNum, Model model){
        EditPasswordLinkInfo id = emailService.checkExpirationDate(random_id, userNum);
        if(id != null){
            model.addAttribute("userNum", id.getUserNum());
            return "OK";
        }else return "NO";
    }
}
