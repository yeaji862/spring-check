package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.check.feedback.FeedBack;
import spring.check.feedback.service.FeedBackServiceImpl;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedBackServiceImpl feedBackService;

    public String upload(@ModelAttribute FeedBack feedBack){
        feedBackService.upload(feedBack);
        return "";
    }

    public String edit(@ModelAttribute FeedBack feedBack){
        feedBackService.edit(feedBack);
        return "";
    }

    public String deleteFeedBack(@RequestParam int seq){
        feedBackService.deleteFeedBack(seq, 0);
        return "";
    }

    public String FeedBackList(@RequestParam int year, @RequestParam int month){
        feedBackService.FeedBackList(0, year, month);
        return "";
    }
}
