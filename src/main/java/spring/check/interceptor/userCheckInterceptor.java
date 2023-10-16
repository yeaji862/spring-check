package spring.check.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import spring.check.plan.dto.FeedBack;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.service.FeedBackServiceImpl;
import spring.check.plan.service.ReadScheduleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class userCheckInterceptor implements HandlerInterceptor {

    private final ReadScheduleServiceImpl readScheduleService;
    private final FeedBackServiceImpl feedBackService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userNum = (Integer) request.getSession().getAttribute("userNum");
        if(userNum == null){
            response.sendRedirect("/");
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
