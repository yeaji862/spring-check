package spring.check.plan.feedback;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Feedback {

    private int seq;
    private int userNum;
    private String feedback_content;
    private String createDate;
}
