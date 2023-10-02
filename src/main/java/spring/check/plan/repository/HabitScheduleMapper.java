package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.HabitSchedule;

import java.util.List;

@Mapper
public interface HabitScheduleMapper {

    @Insert("insert into habitSchedule values (default, #{userNum}, now(), #{habit_month}, #{habit_year}, array_fill(false, ARRAY[#{day}]::int[]))")
    int upload(HabitSchedule habitPlan , int day);

    @Update("update ScheduleDetail set detail_content = #{detail_content} where seq = #{seq} and userNum = #{userNum}")
    int contentEdit(HabitSchedule habitSchedule);

    @Update("update habitSchedule set habit_date_array[#{day}] = #{status} where seq = #{seq} and userNum = #{userNum}")
    int statusEdit(int userNum, boolean status, int day);

    @Delete("DELETE FROM ScheduleDetail WHERE seq = #{seq} AND userNum = #{userNum}") // habitPlan 테이블에 같은 seq userNum 테이터 줄 삭제 트리거 생성
    int deletePlan(int seq, int userNum);

    @Select("SELECT hp.*, d.content\n" +
            "FROM habitSchedule hp\n" +
            "INNER JOIN detail d ON hp.userNum = d.userNum\n" +
            "WHERE hp.userNum = #{userNum} AND hp.habit_year = #{habit_year} AND hp.habit_month = #{habit_month}")
    List<HabitSchedule> planListByDate(String date, int userNum);

    @Select("SELECT hp.*, d.content\n" +
            "FROM habitSchedule hp\n" +
            "INNER JOIN detail d ON hp.userNum = d.userNum WHERE hp.userNum = #{userNum} ORDER BY hp.createDate")
    List<HabitSchedule> planList(int userNum);

}
