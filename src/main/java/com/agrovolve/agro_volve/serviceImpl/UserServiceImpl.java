package com.agrovolve.agro_volve.serviceImpl;

import com.agrovolve.agro_volve.Dto.CreateUserDto;
import com.agrovolve.agro_volve.Dto.UpdateUserDto;
import com.agrovolve.agro_volve.Model.User;
import com.agrovolve.agro_volve.Repository.UserRepository;
import com.agrovolve.agro_volve.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        
        User user = new User();
        user.setUserName(createUserDto.getUserName());
        user.setUserEmail(createUserDto.getUserEmail());
        user.setUserPhone(createUserDto.getUserPhone());
        user.setUserPassword(createUserDto.getUserPassword()); 
        return userRepository.save(user); // Save new user
    }

    @Override
    public User getUserByID(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get(); 
        } else {
            throw new RuntimeException("User not found with ID: " + userId); 
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); 
    }

    @Override
    public User updateUser(Long userId, UpdateUserDto updateUserDto) {
        Optional<User> existingUser = userRepository.findById(userId); 

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

           
            if (updateUserDto.getUserName() != null) {
                userToUpdate.setUserName(updateUserDto.getUserName());
            }
            if (updateUserDto.getUserEmail() != null) {
                userToUpdate.setUserEmail(updateUserDto.getUserEmail());
            }
            if (updateUserDto.getUserPhone() != null) {
                userToUpdate.setUserPhone(updateUserDto.getUserPhone());
            }
            if (updateUserDto.getUserPassword() != null) {
                userToUpdate.setUserPassword(updateUserDto.getUserPassword()); // Or hashed password
            }

            
            return userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId); 
        if (user.isPresent()) {
            userRepository.delete(user.get()); 
        } else {
            throw new RuntimeException("User not found with ID: " + userId); 
        }
    }
}

