package geektime.spring.springbucks.service;

import geektime.spring.springbucks.mapper.CoffeeMapper;
import geektime.spring.springbucks.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public List<Coffee> findAllCoffee() {
        return coffeeMapper.findAll();
    }

    public Coffee findOneCoffee(String name) {
        return coffeeMapper.findByName(name);
    }

    @Cacheable
    public Coffee findOneCoffee(int id) {
        return coffeeMapper.findById(id);
    }

    public List<Coffee> findAllWithParam(int pageNum, int pageSize) {
        return coffeeMapper.findAllWithParam(pageNum, pageSize);
    }
}
