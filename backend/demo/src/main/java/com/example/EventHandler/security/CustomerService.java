package com.example.EventHandler.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomerService implements UserDetails {
    private String name;
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
    }
    public CustomerService(String name,String password){
        this.name=name;
        this.password=password;
    }
    public String getUsername(){
     return name;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
