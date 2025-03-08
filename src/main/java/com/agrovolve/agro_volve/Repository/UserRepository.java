package com.agrovolve.agro_volve.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrovolve.agro_volve.Model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    Optional<User> findUserByEmail(String email);
}
