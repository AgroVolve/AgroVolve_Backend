package com.agrovolve.agro_volve.Model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "users",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email","phone"})
})
public class User  {
   
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int userId;

@NotBlank(message = "user name is required")
@Column(name = "user_name")
private String userName;


@NotBlank(message = "user email is required")
@Column(name = "user_email")
@Email(message = "must me email")
private String userEmail;

@NotBlank(message = "user password is required")
@Column(name = "user_password")
private String userPassword;







    
}
