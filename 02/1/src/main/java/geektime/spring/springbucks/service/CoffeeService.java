package geektime.spring.springbucks.service;

import geektime.spring.springbucks.mapper.CoffeeMapper;
import geektime.spring.springbucks.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public List<Coffee> findAllCoffee() {
        return coffeeMapper.findAll();
    }
}
