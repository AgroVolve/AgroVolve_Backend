package com.agrovolve.agro_volve.Service;
import java.util.*;

public interface UserService {
    
    public void AddUser(User user);
    public User getUserById(int id);
    public List<User> getAllUsers();

}
