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
        InfoSchedule infoPlan = readScheduleService.infoSchedule(9, "2023.10.09");
        LinkedHashMap<Integer, String> dailCalendar = infoPlan.getDailCalendar();
        LinkedHashMap<Integer, String> habitCalendar = infoPlan.getHabitCalender();

        for(int i : dailCalendar.keySet()){
            System.out.println(i + " : " + dailCalendar.get(i));
        }
        System.out.println("===========");
        for(int i : habitCalendar.keySet()){
            System.out.println(i + " : " + habitCalendar.get(i));
        }

        Assertions.assertThat(dailCalendar.size()).isEqualTo(31);
        Assertions.assertThat(habitCalendar.size()).isEqualTo(31);
    }
}
