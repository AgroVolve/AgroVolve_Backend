package com.agrovolve.agro_volve.serviceImpl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agrovolve.agro_volve.Model.User;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;

    public CustomUserDetails(User user) {

        this.email = user.getUserEmail();
        this.password = user.getUserPassword();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    
}
