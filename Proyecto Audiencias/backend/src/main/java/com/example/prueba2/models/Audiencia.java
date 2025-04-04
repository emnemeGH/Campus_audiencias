package com.example.prueba2.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

public class Audiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aud_id;
    private String aud_nombre;
    private LocalDate aud_fecha;
    private LocalTime aud_hora;
    private LocalTime aud_duracion;
    private Integer aud_cuij;
    private String aud_caratula;
    private Integer aud_usrmod;

    @CreationTimestamp  // Se llena automáticamente cuando se crea la audiencia
    @Column(name = "aud_fecins", updatable = false)
    private LocalDateTime audFecIns;

    @UpdateTimestamp  // Se actualiza automáticamente cuando se edita la audiencia
    @Column(name = "aud_fecmod")
    private LocalDateTime audFecMod;

   @ManyToOne
   @JoinColumn(name="sal_id")
    private Sala sala;
    
    public void setAudUsrmod(){
        this.aud_usrmod = (audFecMod == null) ? 0 : aud_usrmod ;
    }
    
    public Integer getAudUsrmod(){
        return aud_usrmod == null? 0 : aud_usrmod;
    }

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum en la BD
    @Column(name = "aud_tipo")
    private EstadoEntidad aud_tipo;

    @Column(name = "aud_estado")
    private Boolean audEstado = true;

    public Boolean getAudEstado() {
        return audEstado;
    }

    public void setAudEstado(Boolean audEstado) {
        this.audEstado = audEstado;
    }

}
