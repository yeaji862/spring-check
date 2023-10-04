package spring.check.plan.service;

import spring.check.plan.dto.DailSchedule;
import spring.check.plan.dto.HabitSchedule;
import spring.check.plan.dto.InfoSchedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ReadScheduleService {

    InfoSchedule infoSchedule(int userNum, int month, int year);
    List<DailSchedule> dailListByDate(int userNum, Date date);
    List<HabitSchedule> habitListByDate(int userNum, Date date);
    HashMap<String, Object> content(int userNum, Date date);

}
