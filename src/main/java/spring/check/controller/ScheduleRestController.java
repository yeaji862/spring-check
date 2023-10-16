package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.check.plan.service.FeedBackServiceImpl;
import spring.check.plan.service.UpdatePlanServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/check/rest")
public class ScheduleRestController {

    private final UpdatePlanServiceImpl updatePlanService;
    private final FeedBackServiceImpl feedBackService;

    @PostMapping("/daily/status")
    int daily(@RequestParam String division,
              @RequestParam(required = false) Optional<Integer> seq,
              @RequestParam(required = false) Optional<String> content,
              @RequestParam(required = false) Optional<String> date,
              HttpSession session){
        return updatePlanService.daily(division, seq, (int) session.getAttribute("userNum"), content, date);
    }

    @PostMapping("/habit/status")
    int habit(@RequestParam String division,
              @RequestParam(required = false) Optional<Integer> seq,
              @RequestParam(required = false) Optional<String> content,
              @RequestParam(required = false) Optional<String> date,
              HttpSession session){
        return updatePlanService.habit(division, seq, (int) session.getAttribute("userNum"), content, date);
    }

    @PostMapping("/daily")
    int achievedDaily(@RequestParam String division,
                      @RequestParam int seq){
        return updatePlanService.achievedDaily(division, seq);
    }

    @PostMapping("/habit")
    int achievedHabit(@RequestParam String division,
                      @RequestParam int seq,
                      @RequestParam String date){
        return updatePlanService.achievedHabit(division, seq, date);
    }

    @PostMapping("/feedback")
    int feedBack(@RequestParam String division, @RequestParam(required = false) String createDate, @RequestParam String content, HttpSession session){
        int userNum = (int) session.getAttribute("userNum");
        return feedBackService.feedBack(division, userNum, createDate, content);
    }
}
