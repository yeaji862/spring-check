package spring.check.user.Service;

import spring.check.user.Members;

public interface UserService {

    int signIn(Members members);
    Members signUp(Members members);
    int editImg(Members members);
    int editPass(Members members);
    String findId(String userId);
}
