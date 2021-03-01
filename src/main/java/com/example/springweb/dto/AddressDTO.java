package com.example.springweb.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {

    private String city;
    private String region;
    private String postCode;

}
