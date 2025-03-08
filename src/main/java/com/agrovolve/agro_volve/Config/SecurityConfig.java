package com.agrovolve.agro_volve.Config;


import java.io.ObjectInputFilter.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.agrovolve.agro_volve.serviceImpl.CustomUserDetailsService;


@Configuration()
@EnableWebSecurity()
@EnableMethodSecurity()
public class SecurityConfig extends WebSecurityConfiguration   {


   

    public  SecurityConfig (CustomUserDetailsService customUserDetailsService){
       
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
      return http.csrf().disable()
                  .authorizeHttpRequests()
                  .requestMatchers("/agro-volve/api/auth/login","/agro-volve/api/auth/register","/agro-volve/api/auth/welc").permitAll()
                  .and()
                  .authorizeHttpRequests()
                  .requestMatchers("/agro-volve/api/auth/**").authenticated()
                  .and()
                  .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .and()
                  .authenticationProvider()
                .build();
                  
    }
   

}

