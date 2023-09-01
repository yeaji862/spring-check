package spring.check.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.user.Service.UserService;
import spring.check.user.repository.UserMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserMapper mapper;
    /**
        로그인 로그아웃 비밀번호 찾기 비밀번호 변경 할 때 암복호화
        로그인 후 메인페이지 퍼센트지 계산
        로직 분리
 **/
    @Override
    public int signIn(Members members) {
        return mapper.signIn(members);
    }

    @Override
    public Members signUp(Members members) {
        return mapper.signUp(members);
    }

    @Override
    public int editImg(Members members) {
        return mapper.editImg(members);
    }

    @Override
    public int editPass(Members members) {
        return mapper.editPass(members);
    }

    @Override
    public String findId(String userId) {
        return mapper.findId(userId);
    }
}
