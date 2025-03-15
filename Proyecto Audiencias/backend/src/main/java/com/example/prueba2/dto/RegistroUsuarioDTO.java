package com.example.prueba2.dto;

import com.example.prueba2.models.Usuario;

public class RegistroUsuarioDTO {
    private Integer usuarioSolicitanteId;
    private Usuario usuario;

    public Integer getUsuarioSolicitanteId() {
        return usuarioSolicitanteId;
    }

    public void setUsuarioSolicitanteId(Integer usuarioSolicitanteId) {
        this.usuarioSolicitanteId = usuarioSolicitanteId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
