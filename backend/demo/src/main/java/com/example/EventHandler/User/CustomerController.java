package com.example.EventHandler.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
  @Autowired
  private CustomerService customerService;
    @PostMapping("/Register")
    public void register(@RequestBody Customer customer){
        customerService.customerRegistration(customer);
  }
    @PostMapping("/EventRegister")
    public void eventRegisterHandler(@RequestParam int task_id,@RequestParam String userName){
        customerService.customerEventRegistration(task_id,userName);
    }
    @GetMapping("/getCustomer")
     public Customer getCustomer(@RequestParam String userName){
        return customerService.getCustomer(userName);
    }
    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustmers();
    }
}
