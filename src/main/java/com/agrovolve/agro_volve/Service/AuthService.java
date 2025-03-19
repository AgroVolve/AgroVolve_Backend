package com.agrovolve.agro_volve.Service;

import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.Dto.LoginResponseDto;
import com.agrovolve.agro_volve.Dto.RegisterDto;

public interface AuthService {
    

    public String registerUser(RegisterDto registerDto);
    public LoginResponseDto loginUser(LoginDto loginDto);
    public String logoutrUser();
    public void requestPasswordReset(String email);
    public  void resetPasword(String token, String newPassword);
    


}
