package spring.check.plan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HabitPlan {

    private int seq;
    private int userNum;
    private boolean status;
    private String createDate;
    private String detail_content;
}
