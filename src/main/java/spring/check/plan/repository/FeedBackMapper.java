package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;
import spring.check.plan.dto.FeedBack;

@Mapper
public interface FeedBackMapper {

    @Select("select * from \"feedBack\" where \"userNum\" = #{userNum} and EXTRACT(MONTH FROM \"createDate\") = #{month} and EXTRACT(year FROM \"createDate\") = #{year}")
    FeedBack feedBackContent(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year);

    @Insert("insert into \"feedBack\" values (default, #{userNum}, #{content}, now(), null)")
    int uploadFeedBack(@Param("userNum") int userNum, @Param("content") String content);

    @Update("update \"feedBack\" set \"fbContent\" = #{content} , \"modifyDate\" = TO_CHAR(NOW(), 'YYYY.MM.DD HH24:MI') where \"userNum\" = #{userNum} and EXTRACT(MONTH FROM \"createDate\") = #{month} and EXTRACT(YEAR FROM \"createDate\") = #{year}")
    int editFeedBack(@Param("userNum") int userNum, @Param("month") int month, @Param("year") int year, @Param("content") String content);
}
