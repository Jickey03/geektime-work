package geektime.spring.springbucks.service;

import geektime.spring.springbucks.mapper.OrderCoffeeMapper;
import geektime.spring.springbucks.mapper.OrderMapper;
import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeOrder;
import geektime.spring.springbucks.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class CoffeeOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderCoffeeMapper orderCoffeeMapper;

    public CoffeeOrder createOrder(String customer, Coffee...coffee) {
        List<Coffee> coffeeList = Arrays.asList(coffee);
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(coffeeList)
                .state(OrderState.INIT.ordinal())
                .build();
        int saved = orderMapper.save(order);
        coffeeList.forEach(o -> {
            orderCoffeeMapper.save(o.getId(), order.getId());
        });
        log.info("New Order: {}", order);
        return order;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.ordinal() <= order.getState()) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state.ordinal());
        orderMapper.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }
}
