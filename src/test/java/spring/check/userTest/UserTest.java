package spring.check.userTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.check.user.Members;
import spring.check.user.service.UserServiceImpl;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserServiceImpl service;

    @Test
    @Transactional
    void signTest(){
        Members members1 = new Members();
        members1.setUserId("test");
        members1.setUserPass("1111");
        members1.setUserImg("testImg");

        int num = service.signUp(members1);

        Assertions.assertThat(num).isEqualTo(1);
    }
}
