package com.example.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.prueba2.models.Sala;

@Repository

public interface SalaRepository extends JpaRepository<Sala, Integer>{
    
}
