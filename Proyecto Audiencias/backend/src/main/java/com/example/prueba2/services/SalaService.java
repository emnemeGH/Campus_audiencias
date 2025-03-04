package com.example.prueba2.services;

import java.util.List;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.services.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Sala;
import com.example.prueba2.repository.SalaRepository;

@Service
public class SalaService extends BaseServiceImpl<Sala, Integer> {
}