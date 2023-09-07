package spring.check.plan.service;

import spring.check.plan.HabitPlan;

import java.util.List;

public interface HabitPlanService {

    int upload(HabitPlan habitPlan , int day);

    int contentEdit(HabitPlan habitPlan);

    int statusEdit(int userNum, boolean status, int day);

    int deletePlan(int seq, int userNum);

    List<HabitPlan> planListByDate(String date, int userNum);

    List<HabitPlan> planList(int userNum);
}
