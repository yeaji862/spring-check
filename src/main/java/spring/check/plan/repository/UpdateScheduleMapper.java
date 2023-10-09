package spring.check.plan.repository;

import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Optional;

@Mapper
public interface UpdateScheduleMapper {

    @Select("select * from daily_content_insert(#{userNum} , #{content})")
    int uploadDaily(@Param("userNum") int userNum, @Param("content") String content);

    @Select("select * from habit_content_insert(#{userNum} , #{content})")
    int uploadHabit(@Param("userNum") int userNum, @Param("content") String content);

    @Update("update daily_content set \"scheduleContent\" = #{content} where seq = #{seq}")
    int editDaily(@Param("seq") int seq, @Param("content") String content);

    @Update("update habit_content set \"scheduleContent\" = #{content} where seq = #{seq}")
    int editHabit(@Param("seq") int seq, @Param("content") String content);

    @Select("select * from daily_content_delete(#{seq})")
    int deleteDaily(@Param("seq") int seq);

    @Select("select * from habit_content_delete(#{seq})")
    int deleteHabit(@Param("seq") int seq);

    @Update("update \"dailySchedule\" set \"achievedDate\" = TO_CHAR(NOW(), 'YYYY.MM.DD HH24:MI'), status = true \n" +
            "where seq = #{seq}")
    int checkOnDaily(@Param("seq") int seq);

    @Update("update \"dailySchedule\" set \"achievedDate\" = null, status = false where seq = #{seq}")
    int checkOffDaily(@Param("seq") int seq);

    @Insert("insert into \"habitAchieved\" (seq, \"userNum\", \"createDate\", status, \"achievedDate\", \"insertDate\")\n" +
            "select seq , \"userNum\" , \"createDate\" , true , TO_CHAR(NOW(), 'YYYY.MM.DD HH24:MI') , #{date}\n" +
            "from \"habitSchedule\" where seq = #{seq}")
    int checkOnHabit(@Param("seq") int seq, @Param("date") LocalDate date);

    @Delete("delete from \"habitAchieved\" where seq = #{seq} and \"insertDate\" = #{date}")
    int checkOffHabit(@Param("seq") int seq, @Param("date") LocalDate date);
}
