package spring.check.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.service.ReadScheduleServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/check")
public class ScheduleController {

    private final ReadScheduleServiceImpl readScheduleService;

    @GetMapping
    String main(HttpSession session, Model model, @RequestParam String date){
        int userNum = (int) session.getAttribute("userNum");
        InfoSchedule infoSchedule = readScheduleService.infoSchedule(userNum, date);
        model.addAttribute("infoSchedule" , infoSchedule);
        model.addAttribute("content", readScheduleService.content(userNum, date));
        return "check/main";
    }
}



