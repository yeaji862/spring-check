package spring.check.userTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.check.editPassLink.service.EditPassLinkServiceImpl;

@SpringBootTest
public class EditPassLinkMapperTest {

    @Autowired
    EditPassLinkServiceImpl editPassLinkService;

    @Test
    @Transactional
    void returnUserNumTest(){
        editPassLinkService.email_sand_info("test" , "kimy_j_@naver.com");
    }
}
