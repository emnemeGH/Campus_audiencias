package com.example.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba2.models.Distrito_judicial;

@Repository

public interface Distrito_judicialRepository extends JpaRepository<Distrito_judicial, Integer>{
    
}
