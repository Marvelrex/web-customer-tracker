package com.jialin.springdemo.controller;

import java.util.List;

import com.jialin.springdemo.Service.CustomerService;
import com.jialin.springdemo.Utility.SortUtils;
import com.jialin.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required = false) String sort) {
		
		// get customers from the service
		List<Customer> theCustomers = null;

		if (sort!=null){
			int sortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(sortField);
		}else {
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		//Create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer",theCustomer);

		return "customer-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){

		Customer customer = customerService.getCustomer(theId);

		theModel.addAttribute("customer",customer);

		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId){
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer){
		customerService.addCustomer(customer);
		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("SearchName")String name, Model model){
		List<Customer>customers = customerService.searchCustomer(name);
		model.addAttribute("customers",customers);
		return "list-customers";
	}
	
}


