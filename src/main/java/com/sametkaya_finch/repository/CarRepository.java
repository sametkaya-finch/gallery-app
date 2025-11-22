package com.sametkaya_finch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sametkaya_finch.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
