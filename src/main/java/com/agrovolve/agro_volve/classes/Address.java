
package com.agrovolve.agro_volve.classes;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
class Address {

    private String district;
    private String sector;
    private String village;

}