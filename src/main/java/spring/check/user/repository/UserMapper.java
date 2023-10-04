package spring.check.user.repository;

import org.apache.ibatis.annotations.*;
import spring.check.user.dto.Members;

@Mapper
public interface UserMapper {


    @Insert("INSERT INTO members VALUES (DEFAULT, #{userPass},'basic', NOW(), #{userMail}, 'default')")
    int signUp(Members members);

    @Select("SELECT * FROM members WHERE \"userMail\" = #{userMail} and division = 'default'")
    Members signIn(Members members);

    @Update("UPDATE members SET \"userImg\" = #{userImg} WHERE \"userNum\" = #{userNum}")
    int editImg(Members members);

    @Update("UPDATE members SET \"userPass\" = #{userPass} WHERE \"userNum\" = #{userNum}")
    int editPass(Members members);

    @Select("SELECT count(*) FROM members WHERE \"userMail\" = #{userMail} and division = 'default'")
    int findId(@Param("userMail") String userMail);

    @Select("select \"userNum\" from members where \"userMail\" = #{userMail} and division = 'default'")
    int getUserNum(@Param("userMail") String userMail);
}
