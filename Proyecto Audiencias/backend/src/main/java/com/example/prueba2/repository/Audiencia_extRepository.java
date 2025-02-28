package com.example.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba2.models.Audiencia_ext;

@Repository

public interface Audiencia_extRepository extends JpaRepository<Audiencia_ext, Integer>{
    
}
