package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.DailPlan;

import java.util.List;

@Mapper
public interface DailPlanMapper {

    @Insert("INSERT INTO dailPlan (userNum, priority, status, createDate)\n" +
            "values (#{userNum} , #{priority} , #{status} , now())\n" +
            "INSERT INTO detail (userNum, content) \n" +
            "values (#{userNum} , #{content})")
    int upload(DailPlan dailPlan);
    @Update("UPDATE detail SET content = #{content} \n" +
            "WHERE userNum = #{userNum}")
    int editPlan(DailPlan dailPlan);
    @Delete("DELETE FROM detail WHERE seq = #{seq} AND userNum = #{userNum}") // DailPlan 테이블에 같은 seq userNum 테이터 줄 삭제 트리거 생성
    int deletePlan(int seq, int userNum);
    @Select("SELECT dp.*, d.content\n" +
            "FROM dailPlan dp\n" +
            "INNER JOIN detail d ON dp.userNum = d.userNum\n" +
            "WHERE dp.userNum = #{userNum} AND dp.createDate = #{date}")
    List<DailPlan> planList(String date, int userNum);
    @Select("SELECT dp.*, d.content\n" +
            "FROM dailPlan dp\n" +
            "INNER JOIN detail d ON dp.userNum = d.userNum WHERE dp.userNum = #{userNum} ORDER BY dp.createDate")
    List<DailPlan> planListByDate(int userNum);
    @Update("UPDATE dailPlan SET status = #{status} WHERE seq = #{seq} AND userNum = #{userNum}")
    int editComplete(int seq, int userNum , boolean status);
    @Update("UPDATE dailPlan SET priority = #{priority} WHERE seq = #{seq} AND userNum = #{userNum}")
    int editPriority(int seq, int userNum , int priority);
}
