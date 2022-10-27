package geektime.spring.springbucks;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeOrder;
import geektime.spring.springbucks.service.CoffeeOrderService;
import geektime.spring.springbucks.service.CoffeeService;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("geektime.spring.springbucks.mapper")
public class SpringBucksApplication implements ApplicationRunner {
	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private CoffeeOrderService coffeeOrderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Bean
	public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Coffee> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	public LettuceClientConfigurationBuilderCustomizer customizer() {
		return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Coffee mocha = coffeeService.findOneCoffee("mocha");
		Coffee latte = coffeeService.findOneCoffee("latte");
		log.info("Coffee {}", mocha);
		log.info("Coffee {}", latte);

		coffeeService.createCoffeeToRedis();
		mocha = coffeeService.findOneCoffee("mocha");
		latte = coffeeService.findOneCoffee("latte");
		log.info("Coffee {}", mocha);
		log.info("Coffee {}", latte);

		CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("Jickey", mocha, latte);

		log.info("Value coffeeOrder: {}", coffeeOrder);

		List<Coffee> coffees = coffeeService.findAllWithParam(1, 3);
		log.info("coffees: {}", coffees);
	}
}

