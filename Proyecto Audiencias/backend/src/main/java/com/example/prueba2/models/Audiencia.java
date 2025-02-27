package com.example.prueba2.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.prueba2.Enums.EstadoAudiencia;


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
    private LocalDate aud_fecmod;
    private boolean aud_estado;
    private LocalDate aud_fecha;
    private LocalTime aud_hora;
    private LocalTime aud_duracion;
    private Integer aud_cuij;
    private String aud_caratula;

   @ManyToOne
   @JoinColumn(name="sal_id")
    private Sala sal_id;
    
    //ACA YA LO VOY A VER, PERO SIN ESTOS METODOS, ME FUNCIONO!!!!!!!!!!!!!
    
    // public void setAudUsrmod(){
    //     this.aud_fecmod = (aud_fecmod == null) ? 0 : aud_fecmod ;
    // }
    
    // public Integer getAudUsrmod(){
    //     return aud_fecmod == null? 0 : aud_fecmod;
    // }

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum en la BD
    @Column(name = "aud_tipo")
    private EstadoAudiencia aud_tipo;

}
