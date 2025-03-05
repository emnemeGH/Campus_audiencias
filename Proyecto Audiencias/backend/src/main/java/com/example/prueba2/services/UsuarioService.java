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
}
