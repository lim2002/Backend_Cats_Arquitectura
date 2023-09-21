package com.arquitectura_backend.arquitecturaBackend.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosDao {

    @Autowired
    private DatosRepository datosRepository;
}
