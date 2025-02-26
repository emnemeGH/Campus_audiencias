package com.example.prueba2.models;

import jakarta.persistence.Entity;
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
    //private dateTime aud_fecins;
    //private dateTime aud_fecmod;
    //private enum aud_estado;
    //private enum aud_tipo;
    //private date fecha
    //private time aud_hora;
    //private time aud_duracion;
    private Integer aud_cuij;
    private String aud_caratula;

    @ManyToOne
    @JoinColumn(name="usr_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="sal_id")
    private Sala sala;
}
