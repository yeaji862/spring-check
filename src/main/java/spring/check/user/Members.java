package spring.check.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Members {

    private int userNum;
    private String userId;
    private String userPass;
    private String userImg;
    private String createDate;
}
