package geektime.spring.springbucks.service;

import geektime.spring.springbucks.mapper.CoffeeMapper;
import geektime.spring.springbucks.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
public class CoffeeService01 {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public Coffee findOneCoffee(String name) {
        return coffeeMapper.findByName(name);
    }
    public List<Coffee> findAllWithParam(int pageNum, int pageSize) {
        return coffeeMapper.findAllWithParam(pageNum, pageSize);
    }
}
