package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.DailSchedule;

import java.util.List;

@Mapper
public interface DailScheduleMapper {

    @Insert("INSERT INTO dailSchedule values (DEFAULT, #{userNum} , #{priority} , #{status} , now()) RETURNING userNum") // detail 테이블에 내용 추가
    int upload(DailSchedule dailPlan);

    @Insert("INSERT INTO ScheduleDetail values (DEFAULT, #{seq} , #{userNum} , #{detail_content})")
    int detailUpload(DailSchedule dailPlan);

    @Update("UPDATE ScheduleDetail SET detail_content = #{detail_content} \n" +
            "WHERE seq = #{seq} AND userNum = #{userNum}")
    int editPlan(DailSchedule dailPlan);

    @Delete("DELETE FROM ScheduleDetail WHERE seq = #{seq} AND userNum = #{userNum}") // DailPlan 테이블에 같은 seq userNum 테이터 줄 삭제 트리거 생성
    int deletePlan(int seq, int userNum);

    @Select("SELECT dp.*, d.content\n" +
            "FROM dailScheduleDetail dp\n" +
            "INNER JOIN detail d ON dp.userNum = d.userNum\n" +
            "WHERE dp.userNum = #{userNum} AND dp.createDate = #{date}")
    List<DailSchedule> planListByDate(String date, int userNum);

    @Select("SELECT dp.*, d.content\n" +
            "FROM dailScheduleDetail dp\n" +
            "INNER JOIN detail d ON dp.userNum = d.userNum WHERE dp.userNum = #{userNum} ORDER BY dp.createDate")
    List<DailSchedule> planList(int userNum);

    @Update("UPDATE dailScheduleDetail SET status = #{status} WHERE seq = #{seq} AND userNum = #{userNum}")
    int editComplete(int seq, int userNum , boolean status);

    @Update("UPDATE dailScheduleDetail SET priority = #{priority} WHERE seq = #{seq} AND userNum = #{userNum}")
    int editPriority(int seq, int userNum , int priority);
}
