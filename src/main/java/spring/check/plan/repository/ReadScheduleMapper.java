package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.Status;

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
}
