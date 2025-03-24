package com.example.prueba2.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.prueba2.models.Sala;

import lombok.Data;

@Data
public class CrearAudienciaDTO {
    private String aud_nombre;
    private LocalDate aud_fecha;
    private LocalTime aud_hora;
    private String aud_caratula;
    private Integer aud_cuij;
    private Boolean aud_estado;
    private String aud_tipo;
    
    private Integer aud_juez;  // ID del juez
    private Integer aud_fiscal; // ID del fiscal
    private Integer aud_defensor; // ID del defensor
    private Sala sala;
}
