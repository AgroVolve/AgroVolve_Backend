package com.agrovolve.agro_volve.Service;


import java.util.List;

import com.agrovolve.agro_volve.Model.User;

public interface UserService {
    
    // public void AddUser(User user);
    // public User getUserById(int id);
    // public List<User> getAllUsers();

     void  createUUser();  
     User getUserByID();
     List<User> getAllUsers();
     
     void updateUser();

     void deleteUser();



}
