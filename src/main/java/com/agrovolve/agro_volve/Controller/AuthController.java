package com.agrovolve.agro_volve.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agrovolve.agro_volve.Dto.ForgotPasswordRequest;
import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.Dto.LoginResponseDto;
import com.agrovolve.agro_volve.Dto.RegisterDto;
import com.agrovolve.agro_volve.Dto.ResetPasswordRequestDto;
import com.agrovolve.agro_volve.Dto.VerifyCodeRequestDto;
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

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authServiceImpl.requestPasswordReset(request.getUserEmail());
        return ResponseEntity.ok("Reset code sent to email");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyCodeRequestDto requestDto) {
        boolean isValid = authServiceImpl.verifyResetCode(requestDto.getEmail(), requestDto.getCode());
        if (isValid) {
            return ResponseEntity.ok("Code verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired code");
        }
    }

    

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDto requestDto) {
        authServiceImpl.resetPassword(requestDto.getEmail(), requestDto.getNewPassword());
        return ResponseEntity.ok("Password reset successful");
    }



    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("This is not protected");
    }
}
