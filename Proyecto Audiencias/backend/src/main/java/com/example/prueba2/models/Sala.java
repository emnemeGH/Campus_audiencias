package com.example.prueba2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sal_id;
    
    private String sal_nombre;
    private String sal_lugar;

    @ManyToOne
    @JoinColumn(name="dis_id")
    private Distrito_judicial distrito;
}
