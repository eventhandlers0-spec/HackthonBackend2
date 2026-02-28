package com.example.EventHandler.security;

import com.example.EventHandler.User.CustomerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
  private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomerRepository customerRepository;
   protected void doFilterInternal(
           HttpServletRequest request,
           HttpServletResponse response,
           FilterChain filterChain
   )throws ServletException, IOException {
       String path=request.getRequestURI();
       if(request.getServletPath().startsWith("/Event")||path.startsWith("/allEvents")||path.startsWith("/getAllCustomers") || path.startsWith("/email") || path.startsWith("/customer")){
           filterChain.doFilter(request, response);
           return;
       }
       else {
           String requestToken = request.getHeader("Authorization");
           String username = null;
           String token = null;
           if (requestToken != null && requestToken.startsWith("Bearer ")) {
               token = requestToken.substring(7);
               username = jwtTokenHelper.getUsernameFromToken(token);
           }
           if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               UserDetails userDetails = userDetailsService.loadUserByUsername(username);
               if (jwtTokenHelper.validateToken(token, userDetails.getUsername())) {
                   UsernamePasswordAuthenticationToken authToken =
                           new UsernamePasswordAuthenticationToken(
                                   userDetails, null, userDetails.getAuthorities()
                           );
                   SecurityContextHolder.getContext().setAuthentication(authToken);
               }
           }
           filterChain.doFilter(request, response);
       }
   }
}
