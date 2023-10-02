package spring.check.plan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HabitSchedule {

    private int seq;
    private int userNum;
    private String createDate;
    private int habit_month;
    private int habit_year;
    private boolean[] habit_date_array;
    private String detail_content;
}
