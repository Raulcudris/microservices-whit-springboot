package com.makiia.users_services.feignclients;
import com.makiia.users_services.model.Bike;
import com.makiia.users_services.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bikes-services")
public interface BikeFeingClient {
    @PostMapping("/bike")
    Bike saveBike(@RequestBody Bike bike);
    @GetMapping("bike/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") Long userId);

}
