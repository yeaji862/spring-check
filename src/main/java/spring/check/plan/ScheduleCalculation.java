package spring.check.plan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.check.plan.dto.Status;

import java.time.LocalDate;
import java.util.*;

@Component
@Slf4j
public class ScheduleCalculation {

    public LinkedHashMap<Integer, Integer> setCalendar(List<Status> daily, List<Status> habit, LinkedHashMap<Integer, Integer> calendar ,
                                                      int year, int month){
        log.info("ScheduleCalculation.LinkedHashMap()");
        HashMap<Integer, Integer> resize = new HashMap<>();
        int actualMaximum = getActualMaximum(year, month);

        for(int i=1; i<=actualMaximum; i++){ // 한 달 일자만큼 배열의 크기를 지정한다 default 값은 false
            resize.put(i, 0);
        }

        /**
         *      해당 날짜의 daily 를 다 달성하면 점수 2점을 더한다
         *      해당 날짜의 habit 을 다 달성하면 점수 3점을 더한다
         */

        for(int i=0; i<daily.size(); i++){
            Integer num = resize.get(daily.get(i).getDay());
            if(daily.get(i).getAchievedCount() == daily.get(i).getTotalCount()) resize.put(daily.get(i).getDay(), num + 2);

        }

        for(int i=0; i<habit.size(); i++){
            Integer num = resize.get(habit.get(i).getDay());
            if(habit.get(i).getAchievedCount() == habit.get(i).getTotalCount()) resize.put(habit.get(i).getDay(), num + 3);

        }

        for(int i=0; i<resizeCalendar(year,month); i++){
            calendar.put(100+i, -1);
        }

        calendar.putAll(resize); // 뷰단에 사용 할 달력의 모양을 수정해준다

        return calendar;
    }

    public int[] achievedCount(List<Status> status, int[] count){
        log.info("ScheduleCalculation.achievedCount()");
        if(status.size() != 0){
            for(int i=0; i<status.size(); i++){
                count[0] += status.get(i).getTotalCount(); // 토탈 카운트
                count[1] += status.get(i).getAchievedCount(); // 달성 카운트
            }
        }
        return count;
    }

    public int achievedPercent(int[] dail, int[] habit) {
        log.info("ScheduleCalculation.achievedPercent()");
        int percent = 0;
        try{
            dail[0] = (dail[0] == 0 && dail[1] == 0) ? 0 : dail[0]/dail[1]*50;
            habit[0] = (habit[0] == 0 && habit[1] == 0) ? 0 : habit[0]/habit[1]*50;
            percent = dail[0] + habit[0]/habit[1];
        }catch (ArithmeticException e){
            log.error(e.getMessage());
        }
        return percent;
    }

    public int getActualMaximum(int year, int month){
        log.info("ScheduleCalculation.getActualMaximum()");
        Calendar instance = Calendar.getInstance();
        instance.set(year, month-1, 1);
        return instance.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private int resizeCalendar(int year, int month){
        log.info("ScheduleCalculation.resizeCalendar()");
        LocalDate instance = LocalDate.of(year, month, 1);
        int dayOfWeek = instance.getDayOfWeek().getValue();
        return (dayOfWeek != 7) ? dayOfWeek : 0; // 7=일요일 해당 달의 1일이 일요일이라면 달력의 모양을 수정 할 필요가 없음
    }

}
