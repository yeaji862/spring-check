package spring.check.user.service;

import org.springframework.web.multipart.MultipartFile;
import spring.check.user.dto.Members;

public interface UserService {

    Members signIn(Members members);
    int signUp(Members members);
    int editImg(int userNum, MultipartFile file);
    int editPass(Members members);
    int findId(String userId);
}
