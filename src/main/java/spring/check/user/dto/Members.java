package spring.check.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @ToString
public class Members {

    private int userNum;
    private String userMail;
    private String userPass;
    private byte[] userImg;
    private Date createDate;
    private String division;
}
