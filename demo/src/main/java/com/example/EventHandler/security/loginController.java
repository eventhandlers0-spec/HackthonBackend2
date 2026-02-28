package com.example.EventHandler.security;

import com.example.EventHandler.User.Customer;
import com.example.EventHandler.User.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    private login login;
   @Autowired
   private CustomerRepository customerRepository;
   @Autowired
   private JwtTokenHelper jwtTokenHelper;
   @Autowired
    private CustomerDetailsService customerDetailsService;
   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody login login){
       try{
           Customer customer=customerRepository.findByUserName(login.getUsername());
           if(customer.getPassword().equalsIgnoreCase(login.getPassword())) {
               String token = jwtTokenHelper.generateToken(customer.getUserName());
               return ResponseEntity.ok(new LoginResponse(token));
           }
           else{
               return ResponseEntity.status(401).body("User not found");
           }
       }
       catch(AuthenticationException e){
           return ResponseEntity.status(401).body("Invalid Username or password");
       }
   }
   static class LoginResponse{
       private String token;
       public LoginResponse(String token){
           this.token=token;
       }
       public String getToken(){
           return token;
       }
       public void setToken(String token){
           this.token=token;
       }
   }
}
