package com.example.springweb.domObj;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="address")
public class AddressDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String region;

    @Column(name = "post_code")
    private String postCode;
}