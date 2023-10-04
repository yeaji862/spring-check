package spring.check.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.service.ReadScheduleServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/check")
public class ScheduleController {

    private final ReadScheduleServiceImpl readScheduleService;

    @GetMapping
    String main3(HttpSession session, Model model, @RequestParam(name = "year", required = false) int year,
                 @RequestParam(name = "month", required = false) int month,
                 @RequestParam(name = "day", required = false) int day){
        int userNum = (int) session.getAttribute("userNum");
        InfoSchedule infoSchedule = readScheduleService.infoSchedule(userNum, month, year);
        model.addAttribute("infoSchedule" , infoSchedule);
        model.addAttribute("content", readScheduleService.content(userNum, new Date()));
        return "";
    }
}
