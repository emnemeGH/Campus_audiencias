package com.example.prueba2.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.dto.CrearAudienciaDTO;
import com.example.prueba2.models.Audiencia;
import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.repository.AudienciaRepository;
import com.example.prueba2.services.AudienciaService;

@RestController
@RequestMapping("/api/audiencias")
@CrossOrigin(origins = "http://localhost:4200")
public class AudienciaController extends BaseController<Audiencia, Integer> {

    private final  AudienciaService audienciaService;
    private final AudienciaRepository audienciaRepository;

    public AudienciaController(AudienciaService audienciaService, AudienciaRepository audienciaRepository) {
        super(audienciaService);
        this.audienciaService = audienciaService;
        this.audienciaRepository = audienciaRepository;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarPorId(@PathVariable Integer id) {
        try {
            audienciaService.borradoLogico(id);
            return ResponseEntity.ok(Map.of("mensaje", "Audiencia eliminada correctamente."));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al eliminar la audiencia."));
        }
    }

    @GetMapping("/{id}/autoridades")
    public ResponseEntity<List<Audiencia_ext>> obtenerAutoridadesPorAudiencia(@PathVariable Long id) {
        List<Audiencia_ext> autoridades = audienciaService.obtenerAutoridadesPorAudiencia(id);
        return ResponseEntity.ok(autoridades);
    }

    // @PreAuthorize("hasRole('OPERADOR')")
    @PostMapping("/crear")
    public ResponseEntity<?> crearAudiencia(@RequestBody CrearAudienciaDTO request) {
        try {
            // Verifica si el request contiene valores válidos
            System.out.println("Recepción del request: " + request);

            Audiencia nuevaAudiencia = audienciaService.guardarAudiencia(request);
            return ResponseEntity.ok(nuevaAudiencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/audienciasPorDistrito/{distritoId}")
    public List<Audiencia> getAudienciasPorDistrito(@PathVariable Long distritoId) {
    return audienciaRepository.findByDistritoId(distritoId);
}


}