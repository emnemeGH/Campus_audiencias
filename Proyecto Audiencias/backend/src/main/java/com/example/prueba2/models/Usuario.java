package com.example.prueba2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity

public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usr_id;
    private String usr_nombre;
    private String usr_username;
    private String usr_mail;
    private Boolean usr_estado;
    private Integer usr_login;
    private String usr_password;
    private Integer usr_isadmin;
    
    @ManyToOne
    @JoinColumn(name="dis_id")
    private Distrito_judicial distrito;

}