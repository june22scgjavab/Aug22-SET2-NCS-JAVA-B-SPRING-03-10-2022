package com.myshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.myshop.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
