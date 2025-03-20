package com.example.prueba2.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private Integer usuarioSolicitanteId; // ID del usuario que crea la audiencia
    private Integer sal_id;
}
