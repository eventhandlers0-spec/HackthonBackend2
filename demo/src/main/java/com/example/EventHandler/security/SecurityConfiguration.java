package com.example.EventHandler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
    @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;
   @Autowired
    private JwtAuthonicationEntryPoint jwtAuthonicationEntryPoint;
   @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    )throws Exception{
       http
               .cors(Customizer.withDefaults())
               .csrf(csrf->csrf.disable())
               .authorizeHttpRequests(auth->auth
//                       .requestMatchers("/home/**","/topic/**").permitAll()
                       .requestMatchers("/allEvents").permitAll()
                       .requestMatchers("/Register").permitAll()
                       .requestMatchers("/login").permitAll()
                       .requestMatchers("/Event/**").permitAll()
                       .requestMatchers("/EventRegister").permitAll()
                       .requestMatchers("/getAllCustomers").permitAll()
                       .requestMatchers("/email/**").permitAll()
                       .requestMatchers("/addEvent").permitAll()
                       .anyRequest().authenticated()
               )
               .exceptionHandling(ex->
                       ex.authenticationEntryPoint(jwtAuthonicationEntryPoint)
                       )
               .sessionManagement(
                       session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               );
       http.addFilterBefore(
               jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
       );
       return http.build();
    }
}
