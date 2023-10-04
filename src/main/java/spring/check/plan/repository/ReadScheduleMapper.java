package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.dto.DailSchedule;
import spring.check.plan.dto.HabitSchedule;
import spring.check.plan.dto.Status;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReadScheduleMapper {

    @Select("select EXTRACT(DAY FROM \"createDate\") AS day,\n" +
            "SUM(CASE WHEN status THEN 1 ELSE 0 END) AS \"achievedCount\",\n" +
            "COUNT(*) AS \"totalCount\" \n" +
            "from \"dailySchedule\"\n" +
            "where \"userNum\" = #{userNum} and EXTRACT(MONTH FROM \"createDate\") = #{month} and EXTRACT(YEAR FROM \"createDate\") = #{year}\n" +
            "group by day \n" +
            "order by day") // 함수 변경
    List<Status> dailScheduleStatus(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year);

    @Select("select EXTRACT(DAY FROM \"createDate\") AS day,\n" +
            "SUM(CASE WHEN status THEN 1 ELSE 0 END) AS \"achievedCount\",\n" +
            "(select count(*) from \"habitSchedule\" where \"userNum\" = #{userNum} and EXTRACT(MONTH FROM \"createDate\") = #{month} and EXTRACT(YEAR FROM \"createDate\") = #{year}) " +
            "AS \"totalCount\" \n" +
            "from \"habitAchieved\"\n" +
            "where \"userNum\" = #{userNum} and EXTRACT(MONTH FROM \"createDate\") = #{month} and EXTRACT(YEAR FROM \"createDate\") = #{year}\n" +
            "group by day \n" +
            "order by day;") // 함수 변경
    List<Status> habitScheduleStatus(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year);

    @Select("SELECT \n" +
            "  (SELECT COUNT(*) FROM \"dailySchedule\" WHERE \"userNum\" = #{userNum} AND status = true) +\n" +
            "  (SELECT COUNT(*) FROM \"habitAchieved\" WHERE \"userNum\" = #{userNum}) AS total_count")
    int allAchievedCount(@Param("userNum") int userNum);

    @Select("SELECT ds.*, dc.scheduleContent\n" +
            "from \"dailySchedule\" ds\n" +
            "INNER JOIN dail_content dc on ds.seq = dc.seq\n" +
            "WHERE ds.\"userNum\" = #{userNum} AND ds.\"createDate\" = #{date}")
    List<DailSchedule> dailListByDate(@Param("userNum") int userNum, Date date);

    @Select("SELECT hs.*, hc.scheduleContent\n" +
            "from \"habitSchedule\" hs\n" +
            "INNER JOIN habit_content hc on hs.seq = hc.seq\n" +
            "WHERE hs.\"userNum\" = #{userNum} AND hs.\"createDate\" = #{date}")
    List<HabitSchedule> habitListByDate(@Param("userNum") int userNum, Date date);

}
