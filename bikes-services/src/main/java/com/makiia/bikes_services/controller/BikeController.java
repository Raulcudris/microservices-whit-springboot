package com.makiia.bikes_services.controller;
import com.makiia.bikes_services.entity.Bike;
import com.makiia.bikes_services.services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;
    @GetMapping()
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") Long id){
        Bike bike = bikeService.getBikeById(id);
        if(bike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike){
        Bike bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> byUserId(@PathVariable("userId") Long userId){
        List<Bike> bike = bikeService.byUserId(userId);
        if(bike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

}
