package com.arquitectura_backend.arquitecturaBackend.Dao;

import com.arquitectura_backend.arquitecturaBackend.Entity.Datos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatosRepository extends JpaRepository<Datos, Long>{

    @Query("SELECT d FROM Datos d")
    public List<Datos> findAll();

}
