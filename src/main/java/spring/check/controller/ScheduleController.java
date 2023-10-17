package spring.check.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.check.cache.ScheduleCache;
import spring.check.plan.service.UpdatePlanServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("/check/view")
public class ScheduleController {

    private final UpdatePlanServiceImpl updatePlanService;
    private final ScheduleCache scheduleCache;

    @GetMapping
    String main(Model model){
        model.addAttribute("planLink", "true");
        return "check/main";
    }

    @GetMapping("/habit")
    String habitMain(Model model){
        model.addAttribute("habitLink", "true");
        return "check/habit_main";
    }

    @GetMapping("/history")
    String yesterdaySchedule(Model model){
        model.addAttribute("historyLink", "true");
        return "check/plan_history";
    }

    @GetMapping("/refresh")
    String refresh(Model model, @RequestParam String date){
        scheduleCache.infoScheduleCacheDelete(date);
        model.addAttribute("planLink", "true");
        return "check/main";
    }

    @GetMapping("/infoMobile")
    String infoMobile(Model model){
        model.addAttribute("mobile", "mobile");
        return "check/info-mobile";
    }

    @GetMapping("/daily/{seq}")
    String achievedDaily(@RequestParam String division,
                         @PathVariable int seq,
                         @RequestParam String date,
                         Model model){
            updatePlanService.achievedDaily(division, seq);
            model.addAttribute("historyLink", "true");
        return "redirect:/check/view/history";
    }

    @GetMapping("/habit/{seq}")
    String achievedHabit(@RequestParam String division,
                         @PathVariable int seq,
                         @RequestParam String date,
                         Model model){
        updatePlanService.achievedHabit(division, seq, date);
        model.addAttribute("historyLink", "true");
        return "redirect:/check/view/history";
    }
}



