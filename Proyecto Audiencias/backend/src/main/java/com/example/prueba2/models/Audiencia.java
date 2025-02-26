package com.example.prueba2.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.prueba2.Enums.Enum.EstadoAudiencia;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Audiencia {
    @Id
    private Integer aud_id;
    private String aud_nombre;
    private LocalDateTime aud_fecins;
    private LocalDateTime aud_fecmod;
    //private enum aud_estado;
    //private enum aud_tipo;
    private LocalDate fecha;
    private LocalTime aud_hora;
    private LocalTime aud_duracion;
    private Integer aud_cuij;
    private String aud_caratula;

   @ManyToOne
   @JoinColumn(name="sal_id")
    private Sala sal_id;

    @ManyToOne
    @JoinColumn(name = "aud_usrins", nullable = false) // Usuario que la creó
    private Usuario usuarioCreacion;

    @ManyToOne
    @JoinColumn(name = "aud_usrmod") // Usuario que la modificó (puede ser null)
    private Usuario usuarioModificacion;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum en la BD
    @Column(name = "aud_tipo")
    private EstadoAudiencia aud_tipo;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum en la BD
    @Column(name = "aud_estado")
    private EstadoAudiencia aud_estado;

}
