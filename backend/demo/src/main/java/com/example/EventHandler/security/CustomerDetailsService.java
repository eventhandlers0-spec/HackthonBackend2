package com.example.EventHandler.security;

import com.example.EventHandler.User.Customer;
import com.example.EventHandler.User.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Customer c=customerRepository.findByName(username);
          Customer  c=customerRepository.findByUserName(userName);
        return new CustomerService(c.getUserName(),c.getPassword());
    }
}
