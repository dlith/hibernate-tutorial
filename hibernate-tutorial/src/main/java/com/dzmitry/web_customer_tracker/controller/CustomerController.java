package com.dzmitry.web_customer_tracker.controller;

import com.dzmitry.web_customer_tracker.entity.Customer;
import com.dzmitry.web_customer_tracker.service.CustomerService;
import com.dzmitry.web_customer_tracker.util.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model, @RequestParam(required = false) String sort){

        List<Customer> customers;
        if(sort != null){
            int sortColumn = Integer.parseInt(sort);
            customers = customerService.getCustomers(sortColumn);
        }else {
            customers = customerService.getCustomers(SortUtils.LAST_NAME);
        }
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId")long id,  Model model){

        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/showFormForDelete")
    public String deleteCustomer(@RequestParam("customerId") long id){
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String name, Model model){

        List<Customer> customers = customerService.searchCustomers(name);
        model.addAttribute("customers", customers);
        return "list-customers";
    }
}
