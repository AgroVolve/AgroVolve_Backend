package com.agrovolve.agro_volve.classes;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Setter
@Getter
public class Contact {

 private int phone;
 private String  alternateEmail;



}
