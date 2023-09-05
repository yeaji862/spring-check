package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.DailPlan;

import java.util.List;

@Mapper
public interface DailPlanMapper {

    @Insert("INSERT INTO dailPlan values (DEFAULT, #{userNum} , #{priority} , #{status} , now()) RETURNING userNum") // detail 테이블에 내용 추가
    int upload(DailPlan dailPlan);

    @Insert("INSERT INTO detail values (DEFAULT, #{seq} , #{userNum} , #{detail_content})")
    int detailUpload(DailPlan dailPlan);

    @Update("UPDATE detail SET detail_content = #{detail_content} \n" +
            "WHERE seq = #{seq} AND userNum = #{userNum}")
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
