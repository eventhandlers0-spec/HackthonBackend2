package com.example.EventHandler.User;

import com.example.EventHandler.Tasks.Task;
import com.example.EventHandler.Tasks.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
  @Autowired
    private CustomerRepository customerRepository;
  @Autowired
    private TaskRepository taskRepository;
  public void customerRegistration(Customer customer){
      customerRepository.save(customer);
  }
  public void customerEventRegistration(int task_id,String userName){
       Customer c=customerRepository.findByUserName(userName);
       Task t=taskRepository.findById(task_id);
       List<Customer> l=t.getCustomers();
       l.add(c);
       t.setCustomers(l);
       c.setTask(t);
       customerRepository.save(c);
       taskRepository.save(t);
  }
  public Customer getCustomer(String userName){
      return customerRepository.findByUserName(userName);
  }
  public List<Customer> getAllCustmers(){
       return customerRepository.findAll();
  }
}
