package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.check.plan.HabitPlanServiceImpl;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/habit")
public class HabitPlanController {

    private final HabitPlanServiceImpl habitPlanService;
}
