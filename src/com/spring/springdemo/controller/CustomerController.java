package com.spring.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.springdemo.dao.CustomerDAO;

import com.spring.springdemo.entity.*;
import com.spring.springdemo.service.CustomerService;
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> customers = customerService.getCustomers();
		
		theModel.addAttribute("customers",customers);
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		//save the customer using service
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdae(@RequestParam("customerId")int theId
			,Model theModel)
	{
		//get the customer from DB
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set the customer as a model attribute to pre-polulated the form
		theModel.addAttribute("customer",theCustomer);
		
		//send over to out form
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId")int theId)
	{
		customerService.deleteCustomer(theId);
		//send over to out form
		return "redirect:/customer/list";
	}
	
}


