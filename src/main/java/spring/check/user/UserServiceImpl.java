package spring.check.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.check.user.Service.UserService;
import spring.check.user.repository.UserMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserMapper mapper;
    /**
        로그인 로그아웃 비밀번호 찾기 비밀번호 변경 할 때 암복호화
        로그인 후 메인페이지 퍼센트지 계산
        로직 분리
 **/
    @Override
    public Members signIn(Members members) {
        log.info("UserServiceImpl signIn()");
        return mapper.signIn(members);
    }

    @Override
    public int signUp(Members members) {
        log.info("UserServiceImpl signUp()");
        return mapper.signUp(members);
    }

    @Override
    public int editImg(Members members) {
        log.info("UserServiceImpl editImg()");
        return mapper.editImg(members);
    }

    @Override
    public String findId(String userId) {
        log.info("UserServiceImpl findId()");
        return mapper.findId(userId);
    }
}
