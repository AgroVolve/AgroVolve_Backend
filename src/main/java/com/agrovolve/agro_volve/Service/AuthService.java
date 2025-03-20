package com.agrovolve.agro_volve.Service;

import com.agrovolve.agro_volve.Dto.LoginDto;
import com.agrovolve.agro_volve.Dto.LoginResponseDto;
import com.agrovolve.agro_volve.Dto.RegisterDto;

public interface AuthService {
    String registerUser(RegisterDto registerDto);
    LoginResponseDto loginUser(LoginDto loginDto);
    String logoutrUser();
    void requestPasswordReset(String email);
    boolean verifyResetCode(String email, String code);
    void resetPassword(String email, String newPassword);
}
