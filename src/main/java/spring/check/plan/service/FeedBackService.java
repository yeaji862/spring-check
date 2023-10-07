package spring.check.plan.service;

import spring.check.plan.dto.FeedBack;

public interface FeedBackService {

    FeedBack feedBackContent(int userNum, int year, int month);
    int feedBack(String division, int userNum, String createDate, String content);
}
