package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.check.plan.dto.Schedule;
import spring.check.plan.service.FeedBackServiceImpl;
import spring.check.plan.service.ReadScheduleServiceImpl;
import spring.check.plan.service.UpdatePlanServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/check/rest")
public class ScheduleRestController {

    private final UpdatePlanServiceImpl updatePlanService;
    private final ReadScheduleServiceImpl readScheduleService;
    private final FeedBackServiceImpl feedBackService;

    @PostMapping("/daily/{userNum}")
    int daily(@RequestParam String division,
              @PathVariable int userNum,
              @RequestParam(required = false) Optional<Integer> seq,
              @RequestParam(required = false) Optional<String> content){
        return updatePlanService.daily(division, seq, userNum, content);
    }

    @PostMapping("/habit/{userNum}")
    int habit(@RequestParam String division,
              @PathVariable int userNum,
              @RequestParam(required = false) Optional<Integer> seq,
              @RequestParam(required = false) Optional<String> content){
        return updatePlanService.habit(division, seq, userNum, content);
    }

    @PostMapping("/daily")
    int achievedDaily(@RequestParam String division, @RequestParam int seq){
        return updatePlanService.achievedDaily(division, seq);
    }

    @PostMapping("/habit")
    int achievedHabit(@RequestParam String division, @RequestParam int seq){
        return updatePlanService.achievedHabit(division, seq);
    }

    @PostMapping("/feedback")
    int feedBack(@RequestParam String division, @RequestParam int seq, @RequestParam String content){
        return feedBackService.feedBack(division, seq, content);
    }

    @PostMapping("/schedule")
    HashMap<String, List<Schedule>> scheduleListByDate(@PathVariable int userNum, @RequestParam String date){
        HashMap<String, List<Schedule>> scheduleList = new HashMap<>();
        scheduleList.put("daily", readScheduleService.dailyListByDate(userNum, date));
        scheduleList.put("habit", readScheduleService.habitListByDate(userNum, date));
        return scheduleList;
    }
}
