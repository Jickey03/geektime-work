package geektime.spring.springbucks.service;

import geektime.spring.springbucks.mapper.CoffeeMapper;
import geektime.spring.springbucks.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class CoffeeService {
    private static final String CACHE = "springbucks-coffee";
    @Autowired
    private CoffeeMapper coffeeMapper;
    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;

    public List<Coffee> findAllCoffee() {
        return coffeeMapper.findAll();
    }

    public Coffee findOneCoffee(String name) {
        HashOperations<String, String, Coffee> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name)) {
            log.info("Get coffee {} from Redis.", name);
            return hashOperations.get(CACHE, name);
        }
        return coffeeMapper.findByName(name);
    }

    public void createCoffeeToRedis() {
        List<Coffee> coffees = coffeeMapper.findAll();
        HashOperations<String, String, Coffee> hashOperations = redisTemplate.opsForHash();
        coffees.forEach(coffee -> {
            log.info("Put coffee {} to Redis.", coffee.getName());
            hashOperations.put(CACHE, coffee.getName(), coffee);
            redisTemplate.expire(CACHE, 1, TimeUnit.MINUTES);
        });
    }

    public List<Coffee> findAllWithParam(int pageNum, int pageSize) {
        return coffeeMapper.findAllWithParam(pageNum, pageSize);
    }
}
