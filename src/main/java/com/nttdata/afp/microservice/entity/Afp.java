package com.nttdata.afp.microservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Afp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
}
