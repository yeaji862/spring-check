package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.dto.FeedBack;

@Mapper
public interface FeedBackMapper {

    @Select("select * from \"feedBack\" where \"userNum\" = #{userNum} and EXTRACT(MONTH FROM \"createDate\") = #{month} and EXTRACT(year FROM \"createDate\") = #{year}")
    FeedBack feedBackContent(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year);

    @Insert("insert into \"feedBack\" (seq , \"fbContent\" , \"createDate\" , \"userNum\")\n" +
            "select seq, #{content}, \"createDate\" , \"userNum\" from \"habitSchedule\" where seq = #{seq}")
    int uploadFeedBack(@Param("seq") int seq, @Param("content") String content);

    @Update("update \"feedBack\" set \"fbContent\" = #{content} , \"modifyDate\" = TO_CHAR(NOW(), 'YYYY.MM.DD HH24:MI') where seq = #{seq}")
    int editFeedBack(@Param("seq") int seq, @Param("content") String content);
}
