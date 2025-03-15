package com.example.prueba2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.prueba2.models.Usuario;
import com.example.prueba2.repository.UsuarioRepository;
import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService extends BaseServiceImpl<Usuario, Integer>{
    
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void borradoLogico(Integer id) {
        usuarioRepository.borrarLogico(id);  // 🔹 Llama al repositorio para actualizar usr_estado
    }

    public Usuario cambiarEstadoAdmin(Integer id, Boolean isAdmin) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    
        usuario.setUsrIsAdmin(isAdmin);
        return usuarioRepository.save(usuario);
    }
    
    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        // Verificar si el nombre de usuario ya existe
        if (usuarioRepository.findByUsrUsername(nuevoUsuario.getUsrUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }
    
        // Verificar si el correo electrónico ya existe
        if (usuarioRepository.findByUsrMail(nuevoUsuario.getUsrMail()).isPresent()) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }
    
        // Verificar que el nombre no esté vacío
        if (nuevoUsuario.getUsrUsername() == null || nuevoUsuario.getUsrUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    
        // Verificar que la contraseña sea segura (mínimo 6 caracteres)
        if (nuevoUsuario.getUsrPassword().length() < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        }
    
        return usuarioRepository.save(nuevoUsuario);
    }
}
