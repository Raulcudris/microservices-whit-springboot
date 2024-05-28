package com.makiia.cars_services.services;
import com.makiia.cars_services.entity.Car;
import com.makiia.cars_services.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public List<Car> getAll(){
        return  carRepository.findAll();
    }

    public Car getCarById( Long id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        Car carNew = carRepository.save(car);
        return carNew;
    }

    public List<Car> byUserId(Long userId) {
        return carRepository.findByUserId(userId);
    }
}
