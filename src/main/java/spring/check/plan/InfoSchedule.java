package spring.check.plan;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Setter @Getter
public class InfoSchedule { // 컨트롤러에서 뷰단으로 보낼 때 플래너 관련 정보들은 하나의 객체로 정의해서 보냄

    private int nowMonth;
    private LinkedHashMap<Integer, String> dailCalendar;
    private LinkedHashMap<Integer, String> habitCalender;
    private int allGoalsNum;
    private int[] dailPlanNum = new int[2];
    private int[] habitPlanNum = new int[2];
    private int goals;

}
