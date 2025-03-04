package com.example.prueba2.controller;
import com.example.prueba2.models.Audiencia;
import com.example.prueba2.services.AudienciaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Usuario;
import com.example.prueba2.services.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController extends BaseController<Usuario, Integer>{
    
    public UsuarioController(UsuarioService service) {
        super(service);
    }
    
}
