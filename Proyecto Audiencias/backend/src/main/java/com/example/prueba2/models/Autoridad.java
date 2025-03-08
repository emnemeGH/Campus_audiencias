package com.example.prueba2.models;

import com.example.prueba2.Enums.EstadoEntidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Autoridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aut_id;
    private String aut_nombre;
    private String aut_mail;
    //private Boolean aut_estado;

    @ManyToOne
    @JoinColumn(name="dis_id")
    private Distrito_judicial distrito;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum en la BD
    @Column(name = "aut_tipo")
    private EstadoEntidad aut_tipo;

    @Column(name = "aut_estado")
    private Boolean autEstado;

    public Boolean getAutEstado() {
        return autEstado;
    }

    public void setAutEstado(Boolean autEstado) {
        this.autEstado = autEstado;
    }
}
