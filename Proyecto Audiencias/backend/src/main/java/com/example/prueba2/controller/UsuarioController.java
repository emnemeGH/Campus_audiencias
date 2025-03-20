package com.example.prueba2.controller;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.prueba2.dto.RegistroUsuarioDTO;
import com.example.prueba2.models.Usuario;
import com.example.prueba2.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController extends BaseController<Usuario, Integer> {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
        this.usuarioService = usuarioService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id) {
        usuarioService.borradoLogico(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/cambiar-admin")
    public ResponseEntity<?> cambiarEstadoAdmin(@PathVariable Integer id, @RequestParam Boolean isAdmin) {
        Usuario usuarioActualizado = usuarioService.cambiarEstadoAdmin(id, isAdmin);
        return ResponseEntity.ok(usuarioActualizado);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO request) {
        // Validar si se pasó `usuarioSolicitanteId`
        if (request.getUsuarioSolicitanteId() == null) {
            return ResponseEntity.badRequest().body("Se requiere el ID del usuario solicitante.");
        }

        // Validar si el usuario solicitante existe
        Usuario usuarioSolicitante = usuarioService.obtenerPorId(request.getUsuarioSolicitanteId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario solicitante no encontrado"));

        // Verificar si el usuario solicitante es ADMIN
        if (!usuarioSolicitante.getUsrIsAdmin()) {
            return ResponseEntity.status(403).body("No tienes permisos para crear usuarios.");
        }

        // Usar el usuario dentro de RegistroUsuarioDTO para crear el nuevo usuario
        Usuario nuevoUsuario = request.getUsuario();  // Obtener el usuario del DTO
        try {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(nuevoUsuario);
            return ResponseEntity.ok(usuarioRegistrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
