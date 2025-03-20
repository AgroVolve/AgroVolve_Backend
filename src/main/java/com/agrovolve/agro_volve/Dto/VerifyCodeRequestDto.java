package com.agrovolve.agro_volve.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCodeRequestDto {

    private String email;
    private String code;
}

