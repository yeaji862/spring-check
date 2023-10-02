package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.check.plan.HabitSchedule;
import spring.check.plan.service.HabitPlanServiceImpl;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/habit")
public class HabitPlanController {

    private final HabitPlanServiceImpl habitPlanService;

    @GetMapping
    public String habitPlanList(Model model){
        List<HabitSchedule> habitSchedule = habitPlanService.planList(0);// 세션에 저장되어 있는 userNum 값
        model.addAttribute("habit", habitSchedule);
        return "";
    }

    //@GetMapping
    public String habitPlanListByDate(@RequestParam String month, Model model){ // 메인 페이지에서 들어올때엔 오늘 날짜 기준으로
        List<HabitSchedule> habitSchedule = habitPlanService.planListByDate(month, 0);// 세션에 저장되어 있는 userNum 값 달 뺴기
        model.addAttribute("habit", habitSchedule);
        return "";
    }

    @PostMapping
    public String upload(@ModelAttribute HabitSchedule habitSchedule, @RequestParam int day){
        habitPlanService.upload(habitSchedule , day);
        return "";
    }

    //@PostMapping
    public String editHabit(@ModelAttribute HabitSchedule habitSchedule){
        habitPlanService.contentEdit(habitSchedule);
        return "";
    }

    //@PostMapping
    public String deleteHabit(@RequestParam int seq){
        habitPlanService.deletePlan(seq , 0);// 세션에 저장되어 있는 userNum 값
        return "";
    }

    //@PostMapping
    public String statusHabit(@RequestParam boolean status, @RequestParam int day){
        habitPlanService.statusEdit(0, status, day);
        return "";
    }
}
