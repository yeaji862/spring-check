package spring.check.editPassLink.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.editPassLink.DateCalculation;
import spring.check.editPassLink.EditPasswordLinkInfo;
import spring.check.editPassLink.repository.EditPassLinkMapper;
import spring.check.mail.MailServiceImpl;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditPassLinkServiceImpl {

    private final MailServiceImpl mailService;
    private final EditPassLinkMapper mapper;

    public void email_sand_info(String random_id, String userId, int userNum){
        log.info("EmailService email_sand_infoInsert()");
        if(mapper.email_sand_infoInsert(random_id, userNum, userId) > 0) mailService.sendMail(userId, random_id, userNum);
    }

    public EditPasswordLinkInfo checkExpirationDate(String random_id, int userNum){
        log.info("EmailService findId()");
        EditPasswordLinkInfo id = mapper.findId(random_id, userNum);

        if(id != null){

            if(new DateCalculation().dateCalculation(id.getSand_date())){
                return id;
            }else return null;

        }else return null;

    }
}
