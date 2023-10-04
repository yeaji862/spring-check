package spring.check.plan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HabitSchedule {

    private int seq;
    private int userNum;
    private String createDate;
    private String achievedDate;
    private boolean status;
    private String scheduleContent;
}
