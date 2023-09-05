package spring.check.mybatisTest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MapperTest {

    @Select("SELECT count(*) FROM information_schema.tables WHERE table_schema = 'public'")
    int dataTest();
}
