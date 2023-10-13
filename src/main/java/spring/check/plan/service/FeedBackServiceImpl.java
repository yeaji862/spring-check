package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.plan.dto.FeedBack;
import spring.check.plan.repository.FeedBackMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackMapper feedBackMapper;

    @Override
    public FeedBack feedBackContent(int userNum, int month, int year) {
        log.info("FeedBackServiceImpl.feedBackContent()");
        return feedBackMapper.feedBackContent(userNum, month, year);
    }
    @Override
    public int feedBack(String division, int userNum, String createDate, String content){
        log.info("FeedBackServiceImpl.feedBack()");
        return (division.equals("edit")) ?
                feedBackMapper.editFeedBack(userNum, Integer.valueOf(createDate.substring(5,7)), Integer.valueOf(createDate.substring(0,4)), content) :
                feedBackMapper.uploadFeedBack(userNum, content);
    }
}
