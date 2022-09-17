package edu.miu.springdataday3.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {
    @Id
    private Long id;
    private String street;
    private Integer zip;
    private String city;

    @OneToOne(mappedBy = "address")
    private User user;

}

//1 st z c
//1 name add_id