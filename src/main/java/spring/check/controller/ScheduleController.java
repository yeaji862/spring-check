package spring.check.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.check.plan.dto.FeedBack;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.service.FeedBackServiceImpl;
import spring.check.plan.service.ReadScheduleServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
@RequestMapping("/check")
public class ScheduleController {

    private final ReadScheduleServiceImpl readScheduleService;
    private final FeedBackServiceImpl feedBackService;

    @GetMapping
    String main(HttpSession session, Model model, @RequestParam String date){
        int userNum = (int) session.getAttribute("userNum");
        FeedBack feedBack = (feedBackService.feedBackContent(userNum, Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(0, 4))));
        InfoSchedule infoSchedule = readScheduleService.infoSchedule(userNum, date);
        model.addAttribute("infoSchedule" , infoSchedule);
        model.addAttribute("content", readScheduleService.content(userNum, date));
        model.addAttribute("feedback" , feedBack);
        model.addAttribute("date", date);
        model.addAttribute("planLink", "true");
        return "check/main";
    }

    @GetMapping("/habit")
    String habitMain(HttpSession session, Model model, @RequestParam String date){
        int userNum = (int) session.getAttribute("userNum");
        InfoSchedule infoSchedule = readScheduleService.infoSchedule(userNum, date);
        model.addAttribute("infoSchedule" , infoSchedule);
        model.addAttribute("content", readScheduleService.content(userNum, date));
        model.addAttribute("feedback" , feedBackService.feedBackContent(userNum, Integer.valueOf(date.substring(5,7)), Integer.valueOf(date.substring(0,4))));
        model.addAttribute("date", date);
        model.addAttribute("habitLink", "true");
        return "check/habit_main";
    }

    @GetMapping("/history")
    String yesterdaySchedule(HttpSession session,Model model, @RequestParam String date){
        int userNum = (int) session.getAttribute("userNum");
        InfoSchedule infoSchedule = readScheduleService.infoSchedule(userNum, date);
        model.addAttribute("infoSchedule" , infoSchedule);
        model.addAttribute("content", readScheduleService.content(userNum, date));
        model.addAttribute("feedback" , feedBackService.feedBackContent(userNum, Integer.valueOf(date.substring(5,7)), Integer.valueOf(date.substring(0,4))));
        model.addAttribute("date", date);
        model.addAttribute("historyLink", "true");
        return "check/plan_history";
    }

    @GetMapping("/refresh")
    String refresh(HttpSession session, Model model, @RequestParam String date){
        int userNum = (int) session.getAttribute("userNum");
        InfoSchedule infoSchedule = readScheduleService.infoScheduleCacheDelete(userNum, date);
        model.addAttribute("infoSchedule" , infoSchedule);
        model.addAttribute("content", readScheduleService.content(userNum, date));
        model.addAttribute("feedback" , feedBackService.feedBackContent(userNum, Integer.valueOf(date.substring(5,7)), Integer.valueOf(date.substring(0,4))));
        model.addAttribute("date", date);
        model.addAttribute("planLink", "true");
        return "check/main";
    }

    @GetMapping("/infoMobile")
    String infoMobile(HttpSession session, Model model, @RequestParam String date){
        int userNum = (int) session.getAttribute("userNum");
        InfoSchedule infoSchedule = readScheduleService.infoScheduleCacheDelete(userNum, date);
        model.addAttribute("infoSchedule" , infoSchedule);
        model.addAttribute("content", readScheduleService.content(userNum, date));
        model.addAttribute("feedback" , feedBackService.feedBackContent(userNum, Integer.valueOf(date.substring(5,7)), Integer.valueOf(date.substring(0,4))));
        model.addAttribute("date", date);
        model.addAttribute("mobile", "mobile");
        return "check/info-mobile";
    }
}



