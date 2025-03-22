package com.example.prueba2.services;

import java.util.Optional;

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

    // Método para registrar un nuevo usuario
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByUsrMail(usuario.getUsrMail())) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }
    
        if (usuarioRepository.existsByUsrUsername(usuario.getUsrUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe.");
        }
    
        return usuarioRepository.save(usuario);
    }
    

    // Cambiar el estado admin de un usuario
    public Usuario cambiarEstadoAdmin(Integer id, Boolean isAdmin) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setUsrIsAdmin(isAdmin);
            return usuarioRepository.save(usuario);
        }
        throw new IllegalArgumentException("Usuario no encontrado");
    }

    // Método para obtener un usuario por ID
    public Optional<Usuario> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Borrado lógico
    @Transactional
    public void borradoLogico(Integer id) {
        usuarioRepository.borrarLogico(id);
    }

}
