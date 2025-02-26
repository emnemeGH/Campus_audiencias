package com.example.prueba2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Autoridad {
    @Id
    private Integer aut_id;
    private String aut_nombre;
    private String aut_mail;
    //private Enum aut_tipo;
    //private Enum aut_estado;

    @ManyToOne
    @JoinColumn(name="dis_id")
    private Distrito_judicial distrito;
}
