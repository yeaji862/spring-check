package spring.check.editPassLink.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import spring.check.editPassLink.EditPasswordLinkInfo;

@Mapper
public interface EditPassLinkMapper {

    @Insert("insert into \"editPasswordLinkInfo\" (\"random_id\", \"userNum\", \"userId\") values (#{random_id}, #{userNum}, #{userId})")
    int email_sand_infoInsert(@Param("random_id") String random_id, @Param("userNum")int userNum, @Param("userId")String userId);

    @Select("select * from \"editPasswordLinkInfo\" where \"random_id\" = #{random_id} and \"userNum\" = #{userNum} and pass_change = 0")
    EditPasswordLinkInfo findId(@Param("random_id")String random_id, @Param("userNum")int userNum);
}