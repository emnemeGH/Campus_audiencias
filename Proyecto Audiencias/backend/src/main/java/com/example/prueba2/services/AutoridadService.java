package com.example.prueba2.services;

import java.util.List;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.services.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.repository.AutoridadRepository;

@Service
public class AutoridadService extends BaseServiceImpl<Autoridad, Integer> {
}
