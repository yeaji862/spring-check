package spring.check.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSenderImpl javaMailSender;

    @Override
    public boolean sendMail(String userId, String random_id, int userNum) {
        log.info("EmailService sendMail()");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String mailText = "안녕하세요! check 입니다.<br>\n" +
                "비밀번호를 변경하시려면 <a href=\"http://localhost:8080/edit/"+random_id+"?userNum="+userNum+"\">여기를</a> 눌러주세요.<br>\n" +
                "링크 유효시간은 24시간입니다.";

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(userId); // 메일 수신자
            mimeMessageHelper.setSubject("<Check> 비밀번호 재설정"); // 메일 제목
            mimeMessageHelper.setText(mailText, true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
