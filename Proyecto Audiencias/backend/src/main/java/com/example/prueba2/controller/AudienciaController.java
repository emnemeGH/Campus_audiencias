package com.example.prueba2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.models.Usuario;
import com.example.prueba2.services.AudienciaService;

@RestController
@RequestMapping("/api/audiencias")
@CrossOrigin(origins = "http://localhost:4200")
public class AudienciaController extends BaseController<Audiencia, Integer> {

    private AudienciaService audienciaService;

    public AudienciaController(AudienciaService audienciaService) {
        super(audienciaService);
        this.audienciaService = audienciaService;
    }

    @DeleteMapping(("/{id}"))
    public void eliminarPorId(@PathVariable Integer id) {
        audienciaService.borradoLogico(id);
    }    

    @GetMapping("/{id}/autoridades")
    public ResponseEntity<List<Audiencia_ext>> obtenerAutoridadesPorAudiencia(@PathVariable Long id) {
        List<Audiencia_ext> autoridades = audienciaService.obtenerAutoridadesPorAudiencia(id);
        return ResponseEntity.ok(autoridades);
    }
    //http://localhost:8080/api/audiencias/crear/{autoridadId}?usuarioSolicitanteId=2
    //{autoridadId} es el id de la autoridad a asignar en la audiencia y "usuarioSolicitanteId" es el id del usuario que quiere crear la audiencia
    //URL para que valide si hay coincidecia de autoridades en audicencias distintas y que la sala no tenga una audiencia asignada en la misma fecha
    @PostMapping("/crear/{autoridadId}")
    public ResponseEntity<?> crearAudiencia(@RequestBody Audiencia audiencia,
                                        @PathVariable Integer autoridadId,
                                        @RequestParam Integer usuarioSolicitanteId) {
    try {
        // Verificar que el usuario solicitante exista
        Usuario usuarioSolicitante = audienciaService.obtenerUsuarioPorId(usuarioSolicitanteId)
            .orElseThrow(() -> new IllegalArgumentException("Usuario solicitante no encontrado"));

        // ❌ Si es admin, no puede gestionar audiencias
        if (usuarioSolicitante.getUsrIsAdmin()) {
            return ResponseEntity.status(403).body("Los administradores no pueden gestionar audiencias.");
        }

        // ✅ Guardar la audiencia (esto ya incluye validaciones de autoridad y sala)
        Audiencia nuevaAudiencia = audienciaService.guardarAudiencia(audiencia, autoridadId);

        return ResponseEntity.ok(nuevaAudiencia);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage()); // ⚠️ Devuelve el error si hay conflicto
    }
}

}