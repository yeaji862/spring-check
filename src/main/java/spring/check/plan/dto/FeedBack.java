package spring.check.plan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class FeedBack {

    private int seq;
    private int userNum;
    private String fbContent;
    private String createDate;
    private String modifyDate;
}
