package com.example.EventHandler.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
  Customer findById(int id);
  Customer findByUserName(String userName);
  List<Customer> findByLocation(String location);

}
