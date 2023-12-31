package spring.check.oauth.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import spring.check.user.dto.Members;

@Mapper
public interface OauthMapper {

    @Select("select * from members where \"division\" = #{division} and \"userMail\"=#{email}")
    Members findId(@Param("email")String email, @Param("division")String division);

    @Insert("insert into members values\n" +
            "(DEFAULT, #{userPass}, null , NOW(), #{userMail}, #{division})")
    int signIn(Members members);

}
