package spring.check.plan.service;

import spring.check.plan.dto.HabitSchedule;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.dto.Schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ReadScheduleService {

    InfoSchedule infoSchedule(int userNum, String date);
    List<Schedule> dailListByDate(int userNum, String date);
    List<Schedule> habitListByDate(int userNum, String date);
    HashMap<String, Object> content(int userNum, String date);


}
