package com.example.prueba2.services;

import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.Enums.EstadoEntidad;
import com.example.prueba2.dto.CrearAudienciaDTO;
import com.example.prueba2.models.Audiencia;
import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.models.Sala;
import com.example.prueba2.models.Usuario;
import com.example.prueba2.repository.AudienciaRepository;
import com.example.prueba2.repository.Audiencia_extRepository;
import com.example.prueba2.repository.AutoridadRepository;
import com.example.prueba2.repository.SalaRepository;
import com.example.prueba2.repository.UsuarioRepository;

@Service
public class AudienciaService extends BaseServiceImpl<Audiencia, Integer> {

    @Autowired
    private AudienciaRepository audienciaRepository;

    @Autowired
    private Audiencia_extRepository audienciaExtRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutoridadRepository autoridadRepository;

    @Autowired
    public AudienciaService(AudienciaRepository audienciaRepository, Audiencia_extRepository audienciaExtRepository,
            SalaRepository salaRepository,
            AutoridadRepository autoridadRepository, UsuarioRepository usuarioRepository) {
        this.audienciaRepository = audienciaRepository;
        this.audienciaExtRepository = audienciaExtRepository;
        this.salaRepository = salaRepository;
        this.autoridadRepository = autoridadRepository;
    }

    @Transactional
    public void borradoLogico(Integer id) {

        // Buscar la audiencia por su ID
        Audiencia audiencia = audienciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Audiencia no encontrada"));

        // Verificar si la audiencia está programada o demorada
        if (audiencia.getAud_tipo() == EstadoEntidad.Programada ||
            audiencia.getAud_tipo() == EstadoEntidad.Demorada) {
            throw new IllegalStateException("No se puede eliminar una audiencia programada o demorada.");
        }

        autoridadRepository.borrarLogico(id);
    }

    public List<Audiencia> obtenerAudienciasActivas() {
        return audienciaRepository.findAudienciasConEstadoTrue();
    }

    public List<Audiencia_ext> obtenerAutoridadesPorAudiencia(Long aud_id) {
        return audienciaExtRepository.findByAudienciaId(aud_id);

    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Audiencia guardarAudiencia(CrearAudienciaDTO request) {
        // ✅ Obtener la sala antes de validarla
        Sala sala = salaRepository.findById(request.getSal_id())
                .orElseThrow(() -> new IllegalArgumentException("Sala no encontrada."));

        // ✅ Validar que la sala esté disponible antes de guardar
        if (audienciaRepository.salaOcupada(sala.getSal_id(), request.getAud_fecha(), request.getAud_hora())) {
            throw new IllegalArgumentException("La sala ya tiene una audiencia en esa fecha y hora.");
        }

        // ✅ Validar que la autoridad no tenga otra audiencia en la misma fecha y hora
        if (audienciaRepository.contarConflictos(request.getAud_juez(), request.getAud_fecha(),
                request.getAud_hora()) > 0 ||
                audienciaRepository.contarConflictos(request.getAud_fiscal(), request.getAud_fecha(),
                        request.getAud_hora()) > 0
                ||
                audienciaRepository.contarConflictos(request.getAud_defensor(), request.getAud_fecha(),
                        request.getAud_hora()) > 0) {
            throw new IllegalArgumentException("Una de las autoridades ya tiene una audiencia en esa fecha y hora.");
        }

        // ✅ Crear la nueva audiencia con la sala obtenida
        Audiencia nuevaAudiencia = new Audiencia();
        nuevaAudiencia.setAud_nombre(request.getAud_nombre());
        nuevaAudiencia.setAud_fecha(request.getAud_fecha());
        nuevaAudiencia.setAud_hora(request.getAud_hora());
        nuevaAudiencia.setAud_caratula(request.getAud_caratula());
        nuevaAudiencia.setAud_cuij(request.getAud_cuij());
        nuevaAudiencia.setAudEstado(request.getAud_estado());
        nuevaAudiencia.setAud_tipo(EstadoEntidad.valueOf(request.getAud_tipo()));
        nuevaAudiencia.setSal_id(sala); // ✅ Usamos la sala obtenida

        // ✅ Guardar la audiencia
        return audienciaRepository.save(nuevaAudiencia);
    }
}