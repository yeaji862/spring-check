package spring.check.mybatisTest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MapperTest {

    @Select("SELECT  COUNT(*)\n" +
            "        FROM   information_schema.tables\n" +
            "        WHERE  table_schema = 'check'")
    int dataTest();
}
