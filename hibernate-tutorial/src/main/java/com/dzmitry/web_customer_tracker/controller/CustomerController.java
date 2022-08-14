package com.dzmitry.web_customer_tracker.controller;


import com.dzmitry.web_customer_tracker.dao.CustomerDAO;
import com.dzmitry.web_customer_tracker.entity.Customer;
import com.dzmitry.web_customer_tracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model){

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);

        return "list-customers";
    }
}
