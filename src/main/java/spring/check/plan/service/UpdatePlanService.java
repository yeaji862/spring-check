package spring.check.plan.service;

import java.util.Optional;

public interface UpdatePlanService {

    int daily(String division, Optional<Integer> seq, int userNum, Optional<String> content, Optional<String> date);
    int habit(String division, Optional<Integer> seq, int userNum, Optional<String> content, Optional<String> date);
    int achievedDaily(String division, int seq);
    int achievedHabit(String division, int seq, String date);
}
