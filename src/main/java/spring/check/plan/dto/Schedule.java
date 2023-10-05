package spring.check.plan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Schedule {

    private int seq;
    private int userNum;
    private boolean status;
    private String createDate;
    private String  achievedDate;
    private String scheduleContent;
}
