package com.agrovolve.agro_volve.Controller;

import com.agrovolve.agro_volve.Dto.CreateUserDto;
import com.agrovolve.agro_volve.Dto.UpdateUserDto;
import com.agrovolve.agro_volve.Model.User;
import com.agrovolve.agro_volve.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agro-volve/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        User createdUser = userService.createUser(createUserDto);

        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "User Created Successfully");

        return new ResponseEntity<>(createdUser, headers, HttpStatus.CREATED);
    }

 
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByID(@PathVariable Long userId) {
        try {
            User user = userService.getUserByID(userId);

           
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "User Retrieved Successfully");

            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
          
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error-Header", "User Not Found");

            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
    }

  
    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "All Users Retrieved Successfully");

        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

   
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDto updateUserDto) {
        try {
            User updatedUser = userService.updateUser(userId, updateUserDto);

            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "User Updated Successfully");

            return new ResponseEntity<>(updatedUser, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
           
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error-Header", "User Not Found");

            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);

            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "User Deleted Successfully");

            return new ResponseEntity<>("User deleted successfully", headers, HttpStatus.OK);
        } catch (RuntimeException e) {
           
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error-Header", "User Not Found");

            return new ResponseEntity<>("User not found", headers, HttpStatus.NOT_FOUND);
        }
    }
}

