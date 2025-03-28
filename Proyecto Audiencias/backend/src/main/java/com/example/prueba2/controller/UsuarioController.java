package com.example.prueba2.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
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

    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable Integer id) {
        usuarioService.borradoLogico(id);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/actualizar")
    public ResponseEntity<?> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody Usuario usuarioActualizado) { // Cambiado para usar @RequestBody
        try {
            Usuario usuarioModificado = usuarioService.actualizarUsuario(id, usuarioActualizado);
            return ResponseEntity
                    .ok(Map.of("mensaje", "Usuario actualizado correctamente.", "usuario", usuarioModificado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrarUsu")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO request) {
        if (request.getUsuario() == null) {
            return ResponseEntity.badRequest().body("Error: No se recibió información del usuario.");
        }

        try {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(request.getUsuario());
            return ResponseEntity.ok(usuarioRegistrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}