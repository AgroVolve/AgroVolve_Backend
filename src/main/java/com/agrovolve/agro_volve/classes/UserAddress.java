
 package com.agrovolve.agro_volve.classes;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
class UserAddress {

 private String district;
 private String sector;
 private String village;

    
}