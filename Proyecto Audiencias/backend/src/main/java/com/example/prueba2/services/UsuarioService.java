package com.example.prueba2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.prueba2.models.Usuario;
import com.example.prueba2.repository.UsuarioRepository;
import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService extends BaseServiceImpl<Usuario, Integer> {

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

        return usuarioRepository.save(usuario); // ✅ Guarda y devuelve el usuario creado
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        
        // Verificar si el nuevo correo ya está en uso por otro usuario
        if (!usuarioExistente.getUsrMail().equals(usuarioActualizado.getUsrMail()) &&
            usuarioRepository.existsByUsrMail(usuarioActualizado.getUsrMail())) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }
        
        // Verificar si el nuevo nombre de usuario ya existe en otro usuario
        if (!usuarioExistente.getUsrUsername().equals(usuarioActualizado.getUsrUsername()) &&
            usuarioRepository.existsByUsrUsername(usuarioActualizado.getUsrUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe.");
        }
        
        // Actualizar datos del usuario
        usuarioExistente.setUsrUsername(usuarioActualizado.getUsrUsername());
        usuarioExistente.setUsrMail(usuarioActualizado.getUsrMail());
        usuarioExistente.setUsrUsername(usuarioActualizado.getUsrUsername());
        usuarioExistente.setUsrPassword(usuarioActualizado.getUsrPassword()); // ⚠️ Considera encriptación si es necesario
        usuarioExistente.setUsrIsAdmin(usuarioActualizado.getUsrIsAdmin());  // Actualizamos el valor de isAdmin
        
        return usuarioRepository.save(usuarioExistente);
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
