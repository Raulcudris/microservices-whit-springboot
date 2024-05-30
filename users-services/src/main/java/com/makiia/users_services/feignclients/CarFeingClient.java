package com.makiia.users_services.feignclients;
import com.makiia.users_services.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cars-services")
//@RequestMapping("/car")
public interface CarFeingClient {
    @PostMapping("/car")
    Car saveCar(@RequestBody Car car);

    @GetMapping("car/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") Long userId);
}
