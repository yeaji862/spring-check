package spring.check.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import spring.check.plan.dto.FeedBack;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.service.FeedBackServiceImpl;
import spring.check.plan.service.ReadScheduleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckPageInterceptor implements HandlerInterceptor {

    private final ReadScheduleServiceImpl readScheduleService;
    private final FeedBackServiceImpl feedBackService;

    /**
     *  session.userNum 값이 없으면 로그인 페이지로 이동 시킨다
     *  사용자가 직접 url 을 수정해 잘못된 형태의 날짜 값이 들어오면 페이지 이동을 막는다
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userNum = (Integer) request.getSession().getAttribute("userNum");
        if(userNum == null){
            response.sendRedirect("/");
            return false;
        }
        try{
            String date = request.getParameter("date");
            if(date != null){
                LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            }else throw new DateTimeException("date 데이터가 존재하지 않습니다.");
        }catch (DateTimeException d){
            log.error(d.getMessage());
            Date today = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String stDate = dateFormat.format(today);
            response.sendRedirect("/check/view?dateEx=0&&date=" + stDate);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
        int userNum = (int) request.getSession().getAttribute("userNum");
        String date = request.getParameter("date");
        FeedBack feedBack = (feedBackService.feedBackContent(userNum, Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(0, 4))));
        InfoSchedule infoSchedule = readScheduleService.infoSchedule(userNum, date);
        HashMap<String, Object> content = readScheduleService.content(userNum, date);

        model.addObject("infoSchedule" , infoSchedule);
        model.addObject("content", content);
        model.addObject("feedback" , feedBack);
        model.addObject("date", date);
    }


}
