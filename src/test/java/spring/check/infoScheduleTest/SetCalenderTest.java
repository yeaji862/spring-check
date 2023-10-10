package spring.check.infoScheduleTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.service.ReadScheduleServiceImpl;

import java.util.LinkedHashMap;

@SpringBootTest
public class SetCalenderTest {

    @Autowired
    private ReadScheduleServiceImpl readScheduleService;

    @Test
    void calenderTest(){
        InfoSchedule infoPlan = readScheduleService.infoSchedule(23, "2023.09.06");
        LinkedHashMap<Integer, Integer> calendar = infoPlan.getCalender();

        for(int i : calendar.keySet()){
            System.out.println(i + " : " + calendar.get(i));
        }

        //Assertions.assertThat(calendar.size()).isEqualTo(31);
    }
}
