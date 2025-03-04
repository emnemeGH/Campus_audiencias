package com.example.prueba2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Distrito_judicial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dis_id;
    private String dis_nombre;
}
