package spring.check.user.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import spring.check.user.Members;

@Mapper
public interface UserMapper {


    @Insert("INSERT INTO members (userId, userPass, userImg, createDate) " +
            "VALUES (#{userId}, #{userPass}, #{userImg}, now())")
    int signIn(Members members);

    @Select("SELECT * FROM members WHERE userId = #{userId} AND userPass =#{userPass}")
    Members signUp(Members members);

    @Update("UPDATE members SET userImg = #{userImg} WHERE userNum = #{userNum}")
    int editImg(Members members);

    @Select("SELECT userNum FROM members WHERE userId = #{userId}")
    String findId(String userId);
}
