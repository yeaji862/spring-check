package spring.check.userTest;

import org.junit.jupiter.api.Test;
import spring.check.editPassLink.CreateRandomId;

public class RandomIdTest {

    @Test
    void randomId(){
        CreateRandomId createRandomId = new CreateRandomId();
        System.out.println(createRandomId.createRandomId());
    }
}
