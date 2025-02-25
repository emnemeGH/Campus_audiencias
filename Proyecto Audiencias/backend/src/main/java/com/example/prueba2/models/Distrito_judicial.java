package com.example.prueba2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Distrito_judicial {
    @Id
    private Integer dis_id;
    private String dis_nombre;
}
