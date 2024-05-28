package com.makiia.bikes_services.services;
import com.makiia.bikes_services.entity.Bike;
import com.makiia.bikes_services.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return  bikeRepository.findAll();
    }

    public Bike getBikeById( Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike){
        Bike bikeNew = bikeRepository.save(bike);
        return bikeNew;
    }

    public List<Bike> byUserId(Long userId) {
        return bikeRepository.findByUserId(userId);
    }
}
