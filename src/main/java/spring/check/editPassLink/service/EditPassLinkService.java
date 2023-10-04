package spring.check.editPassLink.service;

import spring.check.editPassLink.dto.EditPasswordLinkInfo;

public interface EditPassLinkService {

    boolean sendMail(String userId, String random_id);

    int emailSandInfo_Insert(String random_id, String userId, String userNum);

    EditPasswordLinkInfo checkExpirationDate(String random_id, String userNum);

}
