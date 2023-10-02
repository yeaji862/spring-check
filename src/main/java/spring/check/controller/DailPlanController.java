package spring.check.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.check.plan.DailSchedule;
import spring.check.plan.service.DailPlanService;
import spring.check.plan.service.DailPlanServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dail")
public class DailPlanController {

    private final DailPlanServiceImpl dailPlanService;

    @GetMapping
    List<DailSchedule> planList(@RequestParam String date, @RequestParam int userNum){
        return dailPlanService.planListByDate(date, userNum);
    }
    @GetMapping("/date")
    List<DailSchedule> planListByDate(@RequestParam int userNum){
        return dailPlanService.planList(userNum);
    }
}
