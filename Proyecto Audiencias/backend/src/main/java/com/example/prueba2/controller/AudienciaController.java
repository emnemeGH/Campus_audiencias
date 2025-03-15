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
    //http://localhost:8080/api/audiencias/crear/1 URL para que se valide si hay coincidecia de autoridades en audicencias distintas
    @PostMapping("/crear/{autoridadId}")
    public ResponseEntity<?> crearAudiencia(@RequestBody Audiencia audiencia,
                                        @PathVariable Integer autoridadId,
                                        @RequestParam Integer usuarioSolicitanteId) {
    Usuario usuarioSolicitante = audienciaService.obtenerUsuarioPorId(usuarioSolicitanteId)
        .orElseThrow(() -> new IllegalArgumentException("Usuario solicitante no encontrado"));

    // ❌ Si es admin, no puede crear audiencias
    if (usuarioSolicitante.getUsrIsAdmin()) {
        return ResponseEntity.status(403).body("Los administradores no pueden gestionar audiencias.");
    }

    try {
        Audiencia nuevaAudiencia = audienciaService.guardarAudiencia(audiencia, autoridadId);
        return ResponseEntity.ok(nuevaAudiencia);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
}