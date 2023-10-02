package spring.check.plan.service;

import spring.check.plan.HabitSchedule;

import java.util.List;

public interface HabitPlanService {

    int upload(HabitSchedule habitPlan , int day);

    int contentEdit(HabitSchedule habitPlan);

    int statusEdit(int userNum, boolean status, int day);

    int deletePlan(int seq, int userNum);

    List<HabitSchedule> planListByDate(String date, int userNum);

    List<HabitSchedule> planList(int userNum);
}
