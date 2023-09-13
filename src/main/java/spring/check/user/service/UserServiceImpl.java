package spring.check.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.user.Members;
import spring.check.user.UserPassEncoder;
import spring.check.user.repository.UserMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserMapper mapper;
    private final UserPassEncoder passEncoder;

    @Override
    public Members signIn(Members members) {
        log.info("UserServiceImpl signIn()");

        String userPass = members.getUserPass();
        Members user = mapper.signIn(members);

        if(user != null){
            String encodePassword = user.getUserPass();

            if(passEncoder.passwordMatches(userPass,encodePassword)){
                return user;
            }else return null;

        }else return null;

    }

    @Override
    public int signUp(Members members) {
        log.info("UserServiceImpl signUp()");
        members.setUserPass(passEncoder.passwordEncoder(members.getUserPass()));
        return mapper.signUp(members);
    }

    @Override
    public int editImg(Members members) {
        log.info("UserServiceImpl editImg()");
        return mapper.editImg(members);
    }

    @Override
    public int editPass(Members members) {
        log.info("UserServiceImpl editPass()");
        members.setUserPass(passEncoder.passwordEncoder(members.getUserPass()));
        return mapper.editPass(members);
    }

    @Override
    public String findId(String userId) {
        log.info("UserServiceImpl findId()");
        return mapper.findId(userId);
    }
}
