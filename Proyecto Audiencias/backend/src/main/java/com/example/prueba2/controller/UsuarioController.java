package com.example.prueba2.controller;

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
    @PutMapping("/{id}/cambiar-admin")
    public ResponseEntity<?> cambiarEstadoAdmin(@PathVariable Integer id, @RequestParam Boolean isAdmin) {
        Usuario usuarioActualizado = usuarioService.cambiarEstadoAdmin(id, isAdmin);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrarUsu")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO request) {
        if (request.getUsuario() == null) {
            return ResponseEntity.badRequest().body("Error: No se recibió información del usuario.");
        }

        Usuario nuevoUsuario = request.getUsuario();

        try {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(nuevoUsuario);
            return ResponseEntity.ok(usuarioRegistrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
