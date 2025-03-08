package com.agrovolve.agro_volve.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.agrovolve.agro_volve.Model.User;
import com.agrovolve.agro_volve.Repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEMail ) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(userEMail)
        .orElseThrow(() -> new UsernamePasswordAuthenticationToken("User not found with email: " + email));

  return new CustomUserDetails(user);
    }

   
    

}
