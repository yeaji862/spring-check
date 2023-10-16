package spring.check.mybatisTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest{
    @Autowired
    MapperTest mapperTest;
    @Test
    public void dataTest() {
        Assertions.assertThat(mapperTest.dataTest()).isEqualTo(8);
    }
}


