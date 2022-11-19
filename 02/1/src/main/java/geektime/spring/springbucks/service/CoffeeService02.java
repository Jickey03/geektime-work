package geektime.spring.springbucks.service;

import geektime.spring.springbucks.mapper.CoffeeMapper;
import geektime.spring.springbucks.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CoffeeService02 {

    public String sayHolle(String custm) {
        return "Holle " + custm;
    }
}
