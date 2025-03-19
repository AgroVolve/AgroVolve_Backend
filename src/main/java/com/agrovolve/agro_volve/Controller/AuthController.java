package com.agrovolve.agro_volve.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.Dto.LoginResponseDto;
import com.agrovolve.agro_volve.Dto.RegisterDto;
import com.agrovolve.agro_volve.serviceImpl.AuthServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("agro-volve/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @Autowired
    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        String response = authServiceImpl.registerUser(registerDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        LoginResponseDto response = authServiceImpl.loginUser(loginDto);
        return ResponseEntity.ok(response);
  }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("This is not protected");
    }

    // @PostMapping("/forgot-password")
    // public void get

    @PostMapping("/greet")
    public ResponseEntity<String> greet(@RequestBody RegisterDto registerDto) {
        System.out.println("Request received: " + registerDto);
        return ResponseEntity.ok("This is a greeting");
    }
}
