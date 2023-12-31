package spring.check.plan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Setter @Getter
public class InfoSchedule { // 컨트롤러에서 뷰단으로 보낼 때 플래너 관련 정보들은 하나의 객체로 정의해서 보냄

    private int year;
    private String nowMonth;
    private LinkedHashMap<Integer, Integer> calender;
    private int dailCount;
    private int dailAchievedCount;
    private int habitCount;
    private int habitAchievedCount;
    private int achievedPercent;
    private int allAchievedCount;


}
