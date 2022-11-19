package geektime.spring.springbucks;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.service.CoffeeService;
import geektime.spring.springbucks.service.CoffeeService01;
import geektime.spring.springbucks.service.CoffeeService02;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
@MapperScan("geektime.spring.springbucks.mapper")
public class SpringBucksApplication implements ApplicationRunner {
	@Autowired// 注解注入
	private CoffeeService coffeeService;

	@Autowired// 配置类注入
	private CoffeeService01 coffeeService01;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Coffee> coffees = coffeeService.findAllCoffee();
		log.info("--------coffees {}", coffees);

		Coffee mocha = coffeeService01.findOneCoffee("mocha");
		log.info("--------coffee {}", mocha);

		coffees = coffeeService01.findAllWithParam(1, 3);
		log.info("--------coffees: {}", coffees);

		// xml注入
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("coffee.xml");
		CoffeeService02 coffeeService02 = (CoffeeService02) applicationContext.getBean("coffeeService02");
		log.info("--------" + coffeeService02.sayHolle("geekTime"));
	}
}

