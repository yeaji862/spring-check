package spring.check.plan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@Slf4j
public class ScheduleCalculation {

    public LinkedHashMap<Integer, String> setCalendar(List<Status> status, LinkedHashMap<Integer, String> calendar ,
            int year, int month , int day){
        log.info("ScheduleCalculation.LinkedHashMap()");
        HashMap<Integer, String> resize = new HashMap<>();

        for(int i=1; i<=day; i++){ // 한 달 일자만큼 배열의 크기를 지정한다 default 값은 false
            resize.put(i, "false");
        }

        for(int i=0; i<status.size(); i++){ // 해당 일자 계획의 달성여부를 확인하여 모두 다 달성하였을시에 값을 바꿔준다
            if(status.get(i).getAchievedCount() == status.get(i).getTotalCount()) resize.put(status.get(i).getDay(), "true");
        }

        for(int i=0; i<resizeCalendar(year,month); i++){
            calendar.put(i+1000, "false");
        }

        calendar.putAll(resize); // 뷰단에 사용 할 달력의 모양을 수정해준다

        return calendar;
    }

    public int getActualMaximum(int year, int month){
        Calendar instance = Calendar.getInstance();
        instance.set(year, month-1, 1);
        return instance.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private int resizeCalendar(int year, int month){
        LocalDate instance = LocalDate.of(year, month, 1);
        int dayOfWeek = instance.getDayOfWeek().getValue();
        return (dayOfWeek != 7) ? dayOfWeek : 0; // 7=일요일 해당 달의 1일이 일요일이라면 달력의 모양을 수정 할 필요가 없음
    }

}
