package com.dzmitry.web_customer_tracker.dao;

import com.dzmitry.web_customer_tracker.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();
}
