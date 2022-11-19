package geektime.spring.springbucks.controller;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json/coffee")
public class CoffeeJsonController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(path = "/", params = "!name", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Coffee> getAll() {
        return coffeeService.findAllCoffee();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Coffee getById(@PathVariable("id") int id) {
        Coffee coffee = coffeeService.findOneCoffee(id);
        return coffee;
    }

    @GetMapping(path = "/", params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public Coffee getByName(@RequestParam String name) {
        return coffeeService.findOneCoffee(name);
    }
}
