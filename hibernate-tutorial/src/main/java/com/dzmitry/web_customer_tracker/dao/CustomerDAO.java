package com.dzmitry.web_customer_tracker.dao;

import com.dzmitry.web_customer_tracker.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();
    public void saveCustomer(Customer customer);
    public Customer getCustomer(long id);
    public void deleteCustomer(long id);
}
