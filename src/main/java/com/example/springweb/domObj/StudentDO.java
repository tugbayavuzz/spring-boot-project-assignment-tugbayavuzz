package com.example.springweb.domObj;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
@Data
@Entity
@Table(name ="students")
public class StudentDO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)

    private String department;

    private String name;

    private String lastname;

    @OneToOne(cascade =CascadeType.ALL)// customerde değişiklilk oldugunda adress bilgisde etkilensin
    @JoinColumn(name = "address_id")
    private AddressDO address;


}
