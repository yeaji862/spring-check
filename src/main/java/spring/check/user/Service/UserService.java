package spring.check.user.Service;

import spring.check.user.Members;

public interface UserService {

    Members signIn(Members members);
    int signUp(Members members);
    int editImg(Members members);
    String findId(String userId);
}
