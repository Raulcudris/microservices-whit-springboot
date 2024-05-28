package com.makiia.users_services.services;
import com.makiia.users_services.config.RestTemplateConfig;
import com.makiia.users_services.entity.User;
import com.makiia.users_services.feignclients.BikeFeingClient;
import com.makiia.users_services.feignclients.CarFeingClient;
import com.makiia.users_services.model.Bike;
import com.makiia.users_services.model.Car;
import com.makiia.users_services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplateConfig restTemplate;
    @Autowired
    CarFeingClient carFeingClient;

    @Autowired
    BikeFeingClient bikeFeingClient;

    public List<User> getAll(){
        return  userRepository.findAll();
    }

    public User getUserById( Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User userNew = userRepository.save(user);
        return  userNew;
    }

    public List<Car> getCars (Long userId){
        List<Car> cars = restTemplate.restTemplate().getForObject("http://localhost:8002/car/byuser/"+userId,List.class);
        return  cars;
    }

    public List<Bike> getBikes (Long userId){
        List<Bike> bikes = restTemplate.restTemplate().getForObject("http://localhost:8003/bike/byuser/"+userId,List.class);
        return  bikes;
    }

    public  Car saveCar(Long userId , Car car){
        car.setUserId(userId);
        Car carNew = carFeingClient.saveCar(car);
        return  carNew;
    }

    public  Bike saveBike(Long userId , Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeingClient.saveBike(bike);
        return  bikeNew;
    }

    public Map<String, Object> getUserAndVehicles( Long userId ){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("Mensaje", "No existe el usuario");
        }
        result.put("User", user);
        List<Car> cars = carFeingClient.getCars(userId);
        if(cars.isEmpty()){
            result.put("Cars","ese user no tiene coches");
        }else{
            result.put("Cars",cars);
        }
        List<Bike> bikes = bikeFeingClient.getBikes(userId);
        if(bikes.isEmpty()){
            result.put("Bikes","ese user no tiene motos");
        }else{
            result.put("Bikes",bikes);
        }
        return result;

    }

}
