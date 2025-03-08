package com.agrovolve.agro_volve.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.serviceImpl.AuthServiceImpl;

@RestController()
@RequestMapping("agro-volve/api/v1/auth")
public class AuthController {

    @Autowired
    private  AuthServiceImpl authServiceImpl;
    private AuthenticationManager authenticationManager;

     
 
 public void registerUser(){
     
   
 }



 @PostMapping("/login")
 public String login( @RequestBody LoginDto loginDto){

    Authentication authentication= authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(),loginDto.getUserPassword())

    );


   
    if(authentication.isAuthenticated()){

    }


    return "token";

 }

 

}
