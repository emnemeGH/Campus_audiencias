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
    public Usuario cambiarEstadoAdmin(@PathVariable Integer id, @RequestParam Boolean esAdmin) {
        return usuarioService.cambiarEstadoAdmin(id, esAdmin);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario, @RequestParam Integer usuarioSolicitanteId) {
        Usuario usuarioSolicitante = usuarioService.obtenerPorId(usuarioSolicitanteId)
            .orElseThrow(() -> new IllegalArgumentException("Usuario solicitante no encontrado"));
    
        // ❌ Si no es admin, no puede crear usuarios
        if (!usuarioSolicitante.getUsrIsAdmin()) {
            return ResponseEntity.status(403).body("No tienes permisos para crear usuarios.");
        }
    
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
