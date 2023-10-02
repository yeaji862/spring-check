package spring.check.plan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DailSchedule {

    private int seq;
    private int userNum;
    private int priority;
    private boolean status;
    private String createDate;
    private String detail_content;
}
