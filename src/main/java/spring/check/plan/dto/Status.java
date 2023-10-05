package spring.check.plan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Status {

    private int day;
    private  int achievedCount;
    private  int totalCount;
}
