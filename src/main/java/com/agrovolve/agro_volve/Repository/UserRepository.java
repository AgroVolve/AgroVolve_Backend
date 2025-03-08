package com.agrovolve.agro_volve.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrovolve.agro_volve.Model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    User findUserByEmail(String email);
}
