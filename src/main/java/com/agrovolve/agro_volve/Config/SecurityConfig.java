package com.agrovolve.agro_volve.Config;


import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import com.agrovolve.agro_volve.serviceImpl.CustomUserDetailsService;

public class SecurityConfig extends WebSecurityConfiguration   {


    private final CustomUserDetailsService customUserDetailsService;

    public  SecurityConfig (CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService=  customUserDetailsService;
    }

    







}
