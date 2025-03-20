package com.agrovolve.agro_volve.Service;

import java.util.List;

import com.agrovolve.agro_volve.Dto.CreateUserDto;
import com.agrovolve.agro_volve.Dto.UpdateUserDto;
import com.agrovolve.agro_volve.Model.User;

public interface UserService {
    User createUser(CreateUserDto createUserDto);  
    User getUserByID(Long userId);
    List<User> getAllUsers();
    User updateUser(Long userId, UpdateUserDto updateUserDto);
    void deleteUser(Long userId);
}
