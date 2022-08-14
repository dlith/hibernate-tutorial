package com.dzmitry.web_customer_tracker.service;

import com.dzmitry.web_customer_tracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();
    public void saveCustomer(Customer customer);
}
