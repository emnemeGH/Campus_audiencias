package com.example.prueba2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.dto.RegistroUsuarioDTO;
import com.example.prueba2.models.Usuario;
import com.example.prueba2.services.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController extends BaseController<Usuario, Integer>{
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
        this.usuarioService = usuarioService;
    }

    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable Integer id) {
        usuarioService.borradoLogico(id); 
    }

    @PutMapping("/{id}/cambiar-admin")
    public Usuario cambiarEstadoAdmin(@PathVariable Integer id, @RequestParam Boolean isAdmin) {
        return usuarioService.cambiarEstadoAdmin(id, isAdmin);
    }
    //http://localhost:8080/api/usuarios/registrar URL para validar que no haya coincidencias con datos de usuarios que ya estan en la bd
    @PostMapping("/registrar") 
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO request) {
    Integer usuarioSolicitanteId = request.getUsuarioSolicitanteId();
    Usuario usuario = request.getUsuario();

    Usuario usuarioSolicitante = usuarioService.obtenerPorId(usuarioSolicitanteId)
        .orElseThrow(() -> new IllegalArgumentException("Usuario solicitante no encontrado"));

    if (!usuarioSolicitante.getUsrIsAdmin()) {
        return ResponseEntity.status(403).body("No tienes permisos para crear usuarios.");
    }

    Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
    return ResponseEntity.ok(nuevoUsuario);
}

}