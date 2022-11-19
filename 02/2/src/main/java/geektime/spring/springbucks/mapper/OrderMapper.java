package geektime.spring.springbucks.mapper;

import geektime.spring.springbucks.model.CoffeeOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {

    @Insert("insert into t_order (customer, state, create_time, update_time) values (#{customer}, #{state}, now(), now())")
    @Options(useGeneratedKeys = true)
    int save(CoffeeOrder coffeeOrder);
}
