package spring.check.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.editPassLink.repository.EditPassLinkMapper;
import spring.check.user.dto.Members;
import spring.check.user.UserPassEncoder;
import spring.check.user.repository.UserMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserMapper mapper;
    private final EditPassLinkMapper editPassLinkMapper;
    private final UserPassEncoder passEncoder;

    @Override
    public Members signIn(Members members) {
        log.info("UserServiceImpl signIn()");
        String userPass = members.getUserPass();
        Members user = mapper.signIn(members);
        if(user != null){
            String encodePassword = user.getUserPass();
            return (passEncoder.passwordMatches(userPass,encodePassword)) ? user : null;
        }else return null;

    }

    @Override
    public int signUp(Members members) {
        log.info("UserServiceImpl signUp()");
        String userPass = members.getUserPass();
        members.setUserPass(passEncoder.passwordEncoder(members.getUserPass()));
        int num = mapper.signUp(members);
        members.setUserPass(userPass);
        return num;
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
        int returnNum = mapper.editPass(members);
        if(returnNum == 1){
            return editPassLinkMapper.editPass_change(members.getUserNum()); // 비밀번호 변경시 링크 정보 테이블에 업데이트 해준다
        }else return 0;
    }

    @Override
    public int findId(String userMail) {
        log.info("UserServiceImpl findId()");
        return mapper.findId(userMail);
    }
}
