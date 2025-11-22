package com.sametkaya_finch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sametkaya_finch.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
