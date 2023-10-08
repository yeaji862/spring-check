package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.dto.Schedule;
import spring.check.plan.dto.Status;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReadScheduleMapper {

    @Select("select * from daily_schedule_status(#{userNum}, #{month}, #{year})")
    List<Status> dailScheduleStatus(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year);

    @Select("select * from habit_schedule_status(#{userNum}, #{month}, #{year})")
    List<Status> habitScheduleStatus(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year);

    @Select("SELECT \n" +
            "  (SELECT COUNT(*) FROM \"dailySchedule\" WHERE \"userNum\" = #{userNum} AND status = true) +\n" +
            "  (SELECT COUNT(*) FROM \"habitAchieved\" WHERE \"userNum\" = #{userNum}) AS total_count")
    int allAchievedCount(@Param("userNum") int userNum);

    @Select("SELECT ds.*, dc.\"scheduleContent\"\n" +
            "from \"dailySchedule\" ds\n" +
            "INNER JOIN daily_content dc on ds.seq = dc.seq\n" +
            "WHERE ds.\"userNum\" = #{userNum} AND ds.\"createDate\" = #{date}")
    List<Schedule> dailListByDate(@Param("userNum") int userNum, @Param("date") LocalDate date);

    @Select("SELECT hs.*, hc.\"scheduleContent\",\n" +
            "CASE WHEN (SELECT COUNT(*) FROM \"habitAchieved\" WHERE seq = hs.seq and \"insertDate\" = #{date} and status = true) >= 1 THEN 'true' ELSE 'false' END AS status \n" +
            "from \"habitSchedule\" hs\n" +
            "INNER JOIN habit_content hc on hs.seq = hc.seq\n" +
            "WHERE hs.\"userNum\" = #{userNum}\n" +
            "and EXTRACT(MONTH FROM \"createDate\") = #{month} and EXTRACT(YEAR FROM \"createDate\") = #{year}")
    List<Schedule> habitListByDate(@Param("userNum") int userNum, @Param("date") LocalDate date ,@Param("month") int month, @Param("year") int year);

}
