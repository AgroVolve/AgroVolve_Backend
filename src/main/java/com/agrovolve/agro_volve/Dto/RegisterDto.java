package com.agrovolve.agro_volve.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDto {

    @NotBlank(message = "User name is required.")
    @Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters.")
    private String userName;

    @NotBlank(message = "User email is required.")
    @Email(message = "Must be a valid email address.")
    private String userEmail;

    @NotBlank(message = "User password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String userPassword;
}
