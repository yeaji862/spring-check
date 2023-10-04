package spring.check.editPassLink.repository;

import org.apache.ibatis.annotations.*;
import spring.check.editPassLink.dto.EditPasswordLinkInfo;

@Mapper
public interface EditPassLinkMapper {

    @Insert("insert into \"editPasswordLinkInfo\" (random_id, \"userNum\", \"userMail\") values\n" +
            "(#{random_id}\n" +
            ",(select \"userNum\" from members where \"userMail\" = #{userMail} and division = 'default')\n" +
            ",#{userMail})")
    int email_sand_infoInsert(@Param("random_id") String random_id, @Param("userMail")String userMail);

    @Select("select * from \"editPasswordLinkInfo\" where \"random_id\" = #{random_id} and \"userNum\" = #{userNum} and pass_change = 0")
    EditPasswordLinkInfo findId(@Param("random_id")String random_id, @Param("userNum")int userNum);

    @Update("update \"editPasswordLinkInfo\" set pass_change = 1 where \"userNum\" = #{userNum}")
    int editPass_change(@Param("userNum") int userNum);

}