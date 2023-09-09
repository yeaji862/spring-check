package spring.check.feedback.repository;

import org.apache.ibatis.annotations.*;
import spring.check.feedback.FeedBack;

@Mapper
public interface FeedBackMapper {

    @Insert("insert into feedBack values (default, #{seq}, #{userNum}, #{feedback_content}, now())")
    int upload(FeedBack feedBack);

    @Update("update feedBack set feedback_content = #{feedback_content} and modificationDate = now()\n" +
            "where seq = #{seq} and userNum = #{userNum}")
    int edit(FeedBack feedBack);

    @Delete("delete from feedBack where seq = #{seq} and userNum = #{userNum}")
    int deleteFeedBack(int seq, int userNum);

    @Select("select * from \"feedBack\" fb  where \n" +
            "seq = (select seq from \"habitPlan\" hp where habit_year = #{year} and habit_month = #{month}) and userNum = #{userNum}")
    FeedBack FeedBackList(int userNum, int year, int month);
}
