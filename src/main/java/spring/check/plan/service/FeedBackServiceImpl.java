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

    private final FeedBackMapper feedBackContent;

    @Override
    public FeedBack feedBackContent(int userNum, int year, int month) {
        log.info("FeedBackServiceImpl.feedBackContent()");
        return feedBackContent.feedBackContent(userNum, year, month);
    }
}
