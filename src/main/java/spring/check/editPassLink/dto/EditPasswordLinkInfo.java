package spring.check.editPassLink.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class EditPasswordLinkInfo {

    private String random_id;
    private int userNum;
    private String userMail;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sand_date;
    private int pass_change;
}
