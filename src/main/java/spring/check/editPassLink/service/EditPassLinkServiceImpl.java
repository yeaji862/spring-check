package spring.check.editPassLink.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.editPassLink.CreateRandomId;
import spring.check.editPassLink.DateCalculation;
import spring.check.editPassLink.EditPasswordLinkInfo;
import spring.check.editPassLink.repository.EditPassLinkMapper;
import spring.check.mail.MailServiceImpl;
import spring.check.user.repository.UserMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditPassLinkServiceImpl {

    private final MailServiceImpl mailService;
    private final EditPassLinkMapper mapper;
    private final UserMapper userMapper;
    private final DateCalculation dateCalculation;

    public String email_sand_info(String userMail) {
        log.info("EmailService email_sand_infoInsert()");
        CreateRandomId createRandomId = new CreateRandomId();
        String randomId = createRandomId.createRandomId();
        int Num = mapper.email_sand_infoInsert(randomId, userMail);
        if (Num > 0) {
            return (mailService.sendMail(userMail, randomId, userMapper.getUserNum(userMail))) ? "ok" : "fail";
        }else return "fail";
    }

    public EditPasswordLinkInfo checkExpirationDate(String random_id, int userNum){
        log.info("EmailService findId()");
        EditPasswordLinkInfo id = mapper.findId(random_id, userNum);
        if(id != null){
            return (dateCalculation.dateCalculation(id.getSand_date())) ? id : null;
        }else return null;

    }
}
