package com.makiia.bikes_services.repository;
import com.makiia.bikes_services.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    List<Bike> findByUserId(Long userId);
}
