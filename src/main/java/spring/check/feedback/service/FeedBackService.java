package spring.check.feedback.service;

import spring.check.feedback.FeedBack;

public interface FeedBackService {

    int upload(FeedBack feedBack);

    int edit(FeedBack feedBack);

    int deleteFeedBack(int seq, int userNum);

    FeedBack FeedBackList(int userNum, int year, int month);
}
