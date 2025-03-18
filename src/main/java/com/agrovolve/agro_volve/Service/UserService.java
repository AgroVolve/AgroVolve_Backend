package com.agrovolve.agro_volve.Service;


import java.util.List;

import com.agrovolve.agro_volve.Model.User;

public interface UserService {
    
  
     void  createUUser();  
     User getUserByID();
     List<User> getAllUsers();
     
     void updateUser();

     void deleteUser();



}
