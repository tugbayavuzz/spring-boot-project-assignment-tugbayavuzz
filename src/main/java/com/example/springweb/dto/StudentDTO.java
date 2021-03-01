package com.example.springweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String name;
    private String lastName;
    private String department;
    @JsonProperty("address")
    private AddressDTO addressDTO;

}
