package com.agrovolve.agro_volve.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.Dto.LoginResponseDto;
import com.agrovolve.agro_volve.Dto.RegisterDto;
import com.agrovolve.agro_volve.Model.User;
import com.agrovolve.agro_volve.Repository.UserRepository;
import com.agrovolve.agro_volve.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MailService mailService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService,
                           MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    @Override
    public String registerUser(RegisterDto registerDto) {
        if (userRepository.findByUserEmail(registerDto.getUserEmail()).isPresent()) {
            return "Email already exists";
        }

        String hashedPassword = passwordEncoder.encode(registerDto.getUserPassword());

        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setUserEmail(registerDto.getUserEmail());
        user.setUserPassword(hashedPassword);

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public LoginResponseDto loginUser(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUserEmail(),
                            loginDto.getUserPassword()));

            if (authentication.isAuthenticated()) {
                User user = userRepository.findByUserEmail(loginDto.getUserEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                String token = jwtService.generateToken(loginDto.getUserEmail());
                return new LoginResponseDto(token, user.getUserName(), user.getUserEmail());
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
                return null;
    }

    @Override
    public void requestPasswordReset(String email) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String resetCode = String.valueOf((int)(Math.random() * 999999));

        user.createResetToken(resetCode);
        userRepository.save(user);

        String body = "Your password reset code is: <b>" + resetCode + "</b>";
        mailService.sendEmail(user.getUserEmail(), "Reset Password", body);
    }

    @Override
    public boolean verifyResetCode(String email, String code) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.isResetTokenValid(code);
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserPassword(passwordEncoder.encode(newPassword));
        user.clearResetToken();
        userRepository.save(user);
    }

    @Override
    public String logoutrUser() {
       return "about to log out";
    }
}
