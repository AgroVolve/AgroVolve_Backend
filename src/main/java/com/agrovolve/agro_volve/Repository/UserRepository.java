package com.agrovolve.agro_volve.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrovolve.agro_volve.Model.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String userEmail); 
    Optional<User>  findByResetToken(String token);

}

