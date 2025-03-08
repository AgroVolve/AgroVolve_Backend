package com.agrovolve.agro_volve.Model;


import com.agrovolve.agro_volve.classes.UserContact;


import jakarta.persistence.Column;

import jakarta.persistence.Embedded;
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
public class User<UserAddress>  {
   
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long  userId;

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

@Embedded
private UserAddress address;

@Embedded
private UserContact contact;









    
}
