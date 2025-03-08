package com.agrovolve.agro_volve.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.agrovolve.agro_volve.serviceImpl.CustomUserDetailsService;


@Configuration()
@EnableWebSecurity()
@EnableMethodSecurity()
public class SecurityConfig   {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    };

   

    


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
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
                  .authenticationProvider(authenticationProvider())
                  .build();


                  
    }

     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


   

}

