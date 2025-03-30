package com.example.prueba2.models;

import jakarta.persistence.Column;
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
    private Integer usr_login;
    
    @ManyToOne
    @JoinColumn(name="dis_id")
    private Distrito_judicial distrito;

    public Distrito_judicial getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito_judicial distrito) {
        this.distrito = distrito;
    }

    @Column(name = "usr_estado")
    private Boolean usrEstado = true;

    public Boolean getUsrEstado() {
        return usrEstado;
    }

    public void setUsrEstado(Boolean usrEstado) {
        this.usrEstado = usrEstado;
    }

    @Column(name = "usr_isadmin")
    private Boolean usrIsAdmin; // ✅ Indica si el usuario es admin (true = admin, false = normal)

    public Boolean getUsrIsAdmin() {
        return usrIsAdmin;
    }
    
    public void setUsrIsAdmin(Boolean usrIsAdmin) {
        this.usrIsAdmin = usrIsAdmin;
    }

    @Column(name = "usr_nombre")
    private String usrNombre;

    public String getUsrNombre() {
        return usrNombre;
    }

    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    @Column(name = "usr_username")
    private String usrUsername;

    public String getUsrUsername() {
        return usrUsername;
    }

    public void setUsrUsername(String usrUsername) {
        this.usrUsername = usrUsername;
    }

    @Column(name = "usr_mail")
    private String usrMail;

    public String getUsrMail() {
        return usrMail;
    }

    public void setUsrMail(String usrMail) {
        this.usrMail = usrMail;
    }

    @Column(name = "usr_password")
    private String usrPassword;

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }
}