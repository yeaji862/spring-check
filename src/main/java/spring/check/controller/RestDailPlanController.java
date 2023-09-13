package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.check.plan.DailPlan;
import spring.check.plan.service.DailPlanServiceImpl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/Rest-Dail")
public class RestDailPlanController {

    private final DailPlanServiceImpl dailPlanService;

    @PostMapping
    public int upload(@ModelAttribute DailPlan dailPlan){
        return dailPlanService.upload(dailPlan);
    }
    @PatchMapping
    public int editPlan(@ModelAttribute DailPlan dailPlan){
        return dailPlanService.editPlan(dailPlan);
    }
    @DeleteMapping
    int deletePlan(@RequestParam int seq, @RequestParam int userNum){
        return dailPlanService.deletePlan(seq , userNum);
    }
    @GetMapping("/{userNum}/status")
    int editComplete(@RequestParam int seq, @RequestParam int userNum , @RequestParam boolean status){
        return dailPlanService.editComplete(seq, userNum, status);
    }
    @GetMapping("/{userNum}/priority")
    int editPriority(@RequestParam int seq, @RequestParam int userNum , @RequestParam int priority){
        return dailPlanService.editPriority(seq, userNum, priority);
    }

}
