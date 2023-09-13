package spring.check.mail;

public interface MailService {

    boolean sendMail(String userId, String random_id, int userNum);
}
