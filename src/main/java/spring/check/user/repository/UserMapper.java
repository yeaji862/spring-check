package spring.check.user.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import spring.check.user.Members;

@Mapper
public interface UserMapper {


    @Insert("INSERT INTO members VALUES (DEFAULT, #{userPass},'basic', NOW(), #{userMail}, 'default') RETURNING \"userNum\"")
    int signUp(Members members);

    @Select("SELECT * FROM members WHERE \"userMail\" = #{userMail} and division = 'default'")
    Members signIn(Members members);

    @Update("UPDATE members SET \"userImg\" = #{userImg} WHERE \"userNum\" = #{userNum}")
    int editImg(Members members);

    @Update("UPDATE members SET \"userPass\" = #{userPass} WHERE \"userNum\" = #{userNum}")
    int editPass(Members members);

    @Select("SELECT \"userNum\" FROM members WHERE \"userMail\" = #{userMail} and and division = 'default'")
    String findId(String userMail);
}
