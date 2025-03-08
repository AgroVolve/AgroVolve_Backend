    package com.agrovolve.agro_volve.Controller;

    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.AuthenticationManager;
   
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.agrovolve.agro_volve.Dto.LoginDto;
       import com.agrovolve.agro_volve.serviceImpl.JwtService;

    @RestController()
    @RequestMapping("agro-volve/api/v1/auth")
    public class AuthController {

        // @Autowired
        // private AuthServiceImpl authServiceImpl;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtService jwtService;

        public void registerUser() {

        }

        @PostMapping("/login")
        public String login(@RequestBody LoginDto loginDto) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getUserPassword())

            );

            if (authentication.isAuthenticated()) {

                return jwtService.generateTOken(loginDto.getUserEmail());
            } else {
                throw new UsernameNotFoundException("Invalid username");
            }

        }

        @GetMapping("/welcome")
        public String welcome() {
            return "This is not protected";
        }

    }
