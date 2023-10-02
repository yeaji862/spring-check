package spring.check.plan.service;

import spring.check.plan.DailSchedule;
import spring.check.plan.HabitSchedule;
import spring.check.plan.InfoSchedule;

import java.util.List;

public interface ReadScheduleService {

    InfoSchedule infoSchedule(int userNum, int month, int year);
    List<DailSchedule> dailListByDate(String date, int userNum);
    List<HabitSchedule> habitListByDate(String date, int userNum);
}
