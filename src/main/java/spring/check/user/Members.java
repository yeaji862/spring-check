package spring.check.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @ToString
public class Members {

    private int userNum;
    private String userId;
    private String userPass;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirth;
    private String userImg;
    private Date createDate;
}
