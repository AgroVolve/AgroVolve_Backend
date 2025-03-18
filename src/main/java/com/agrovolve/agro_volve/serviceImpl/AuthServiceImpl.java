package com.agrovolve.agro_volve.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.Dto.RegisterDto;
import com.agrovolve.agro_volve.Model.User;
import com.agrovolve.agro_volve.Repository.UserRepository;
import com.agrovolve.agro_volve.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
  private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public String registerUser(RegisterDto registerDto) {

        if (userRepository.findByUserEmail(registerDto.getUserEmail()).isPresent()) {
            return "Email is already exist";
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
    public String loginUser(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getUserPassword())

        );

        if (authentication.isAuthenticated()) {

            return jwtService.generateToken(loginDto.getUserEmail());
        } else {
            throw new UsernameNotFoundException("Invalid username");
        }

    }

    @Override
    public String logoutrUser() {
        return "Logout here";
    }
}
