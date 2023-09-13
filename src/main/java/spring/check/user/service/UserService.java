package spring.check.user.service;

import spring.check.user.Members;

public interface UserService {

    Members signIn(Members members);
    int signUp(Members members);
    int editImg(Members members);
    int editPass(Members members);
    String findId(String userId);
}
