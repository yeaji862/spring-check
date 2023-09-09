package spring.check.feedback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.feedback.repository.FeedBackMapper;
import spring.check.feedback.service.FeedBackService;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackMapper mapper;

    @Override
    public int upload(FeedBack feedBack) {
        return mapper.upload(feedBack);
    }

    @Override
    public int edit(FeedBack feedBack) {
        return mapper.edit(feedBack);
    }

    @Override
    public int deleteFeedBack(int seq, int userNum) {
        return mapper.deleteFeedBack(seq, userNum);
    }

    @Override
    public FeedBack FeedBackList(int userNum, int year, int month) {
        return mapper.FeedBackList(userNum, year, month);
    }
}
