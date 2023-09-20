package spring.check.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @ToString
public class Members {

    private int userNum;
    private String userId; // delete
    private String userMail;
    private String userPass;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirth; // delete
    private String userImg;
    private Date createDate;
    private String division;
}
