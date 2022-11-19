package geektime.spring.springbucks.config;

import geektime.spring.springbucks.service.CoffeeService01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoffeeConfig {
    @Bean
    public CoffeeService01 coffeeService01() {
        return new CoffeeService01();
    }
}
