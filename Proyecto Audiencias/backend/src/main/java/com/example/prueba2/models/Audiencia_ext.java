package com.example.prueba2.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Audiencia_ext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eau_id;
    private String eau_nombre;
    private String eau_usrins;
    private LocalDate eau_fecins;
    private Integer eau_usrmod;
    private LocalDate eau_fecmod;

    @ManyToOne
    @JoinColumn(name="aut_id")
    private Autoridad aut_id;

    @ManyToOne
    @JoinColumn(name="aud_id")
    private Audiencia aud_id;

    @Column(name = "eau_estado")
    private Boolean eauEstado;

    public Boolean getEauEstado() {
        return eauEstado;
    }

    public void setEauEstado(Boolean eauEstado) {
        this.eauEstado = eauEstado;
    }
}