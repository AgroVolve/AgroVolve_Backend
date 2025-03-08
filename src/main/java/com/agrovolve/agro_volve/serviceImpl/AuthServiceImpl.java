package com.agrovolve.agro_volve.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;

import com.agrovolve.agro_volve.Repository.UserRepository;
import com.agrovolve.agro_volve.Service.AuthService;

public class AuthServiceImpl implements AuthService {

    

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser() {
      //logic

    }

    @Override
    public void loginUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginUser'");
    }

    @Override
    public void logoutrUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logoutrUser'");
    }
    
}
