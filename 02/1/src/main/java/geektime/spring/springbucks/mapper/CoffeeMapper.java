package geektime.spring.springbucks.mapper;

import geektime.spring.springbucks.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CoffeeMapper {
    @Select("select * from t_coffee")
    List<Coffee> findAll();

    @Select("select * from t_coffee where name = #{name}")
    @Results({@Result(id = true, column = "id", property = "id"), @Result(column = "create_time", property = "createTime")})
    Coffee findByName(@Param("name") String name);

    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithParam(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
