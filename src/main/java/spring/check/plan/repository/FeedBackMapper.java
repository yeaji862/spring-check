package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.dto.FeedBack;

@Mapper
public interface FeedBackMapper {

    @Select("select fb.feedback_content , fb.\"modificationDate\" \n" +
            "from \"habitSchedule\" hs \n" +
            "inner join \"feedBack\" fb on hs.seq = fb.seq \n" +
            "where hs.\"userNum\" = #{userNum} and EXTRACT(MONTH FROM hs.\"createDate\") = #{month} and EXTRACT(year FROM hs.\"createDate\") = #{year}")
    FeedBack feedBackContent(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year);
}
