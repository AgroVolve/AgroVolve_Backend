package com.agrovolve.agro_volve.serviceImpl;

import java.util.UUID;

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
import com.agrovolve.agro_volve.classes.MailService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    MailService mailService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

        throw new UsernameNotFoundException("Invalid username or password");
    }

    @Override
    public String logoutrUser() {
        return "Logout successful";
    }

    @Override
    public void requestPasswordReset(String email) {

        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("User not FOund"));

        String resetTOken = UUID.randomUUID().toString();

        user.createResetToken(resetTOken);

        userRepository.save(user);

        mailService.sendEmail(user.getUserEmail(), "Reset Password", "Here is your reset link: ...");

    }

    @Override
    public void resetPasword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (!user.isResetTokenValid(token)) {
            throw new RuntimeException("Invalid or expired token");
        }

        user.setUserPassword(passwordEncoder.encode(newPassword));
        user.clearResetToken();

        userRepository.save(user);
    }

}
