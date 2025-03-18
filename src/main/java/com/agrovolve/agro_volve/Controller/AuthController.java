package com.agrovolve.agro_volve.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.Dto.RegisterDto;
import com.agrovolve.agro_volve.serviceImpl.AuthServiceImpl;


@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("agro-volve/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto registerDto) {
         System.out.println(registerDto);
        authServiceImpl.registerUser(registerDto);
          
        return "user registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {

       
     return  authServiceImpl.loginUser(loginDto);
        

    }

    @GetMapping("/welcome")
    public String welcome() {
        return "This is not protected";
    }


    @PostMapping("/greet")
    public String greet(@RequestBody RegisterDto registerDto) {
        System.out.println("regquest");
        System.out.println(registerDto);
        return "This is a greating";
    }

}


