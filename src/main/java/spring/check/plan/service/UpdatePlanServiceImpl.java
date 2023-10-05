package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.plan.repository.UpdateScheduleMapper;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdatePlanServiceImpl implements UpdatePlanService{

    private final UpdateScheduleMapper updateScheduleMapper;

    @Override
    public int daily(String division, Optional<Integer> seq, int userNum, Optional<String> content) {
        int num = 0;
        switch (division){
            case "upload" :
                num = updateScheduleMapper.uploadDaily(userNum, content); break;
            case "edit" :
                num = updateScheduleMapper.editDaily(seq, content); break;
            case "delete" :
                num = updateScheduleMapper.deleteDaily(seq); break;
        }

        return num;
    }

    @Override
    public int habit(String division, Optional<Integer> seq, int userNum, Optional<String> content) {
        int num = 0;
        switch (division){
            case "upload" :
                num = updateScheduleMapper.uploadHabit(userNum, content); break;
            case "edit" :
                num = updateScheduleMapper.editHabit(seq, content); break;
            case "delete" :
                num = updateScheduleMapper.deleteHabit(seq); break;
        }

        return num;
    }
    @Override
    public int achievedDaily(String division, int seq){
        return (division.equals("on")) ? updateScheduleMapper.checkOnDaily(seq) : updateScheduleMapper.checkOffDaily(seq);
    }
    @Override
    public int achievedHabit(String division, int seq){
        return (division.equals("on")) ? updateScheduleMapper.checkOnHabit(seq) : updateScheduleMapper.checkOffHabit(seq);
    }
}
