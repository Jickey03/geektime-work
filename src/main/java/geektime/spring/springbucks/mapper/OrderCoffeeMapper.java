package geektime.spring.springbucks.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderCoffeeMapper {

    @Insert("insert into t_order_coffee (coffee_order_id, items_id) values (#{orderId}, #{coffeeId})")
    int save(Long orderId, Long coffeeId);
}
