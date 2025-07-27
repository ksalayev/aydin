package com.srscons.aydin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "students") // You can rename the table if needed
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private Integer age;

    private String university;


}