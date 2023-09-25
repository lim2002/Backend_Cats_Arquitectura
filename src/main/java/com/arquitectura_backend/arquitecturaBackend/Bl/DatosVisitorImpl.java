package com.arquitectura_backend.arquitecturaBackend.Bl;

import com.arquitectura_backend.arquitecturaBackend.Dao.DatosRepository;
import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;
import com.arquitectura_backend.arquitecturaBackend.Entity.Datos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class DatosVisitorImpl implements DatosVisitor{
    private static final Logger logger = LoggerFactory.getLogger(DatosVisitorImpl.class);

    @Autowired
    private DatosRepository datosRepository;

    @Override
    public void visitGuardar(ResponseDto responseDto) {
        logger.info("Visitando método para guardar datos");
        try {
            Datos datosDto1 = new Datos();
            datosDto1.setDato(responseDto.getMensaje());
            datosDto1.setIdTipo(1);
            logger.debug("datosDto1: {}", datosDto1);
            datosRepository.save(datosDto1);
            logger.info("Datos guardados exitosamente");
        } catch (Exception e) {
            logger.error("Error al guardar datos: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void visitModificar(ResponseDto responseDto, Integer id) {
        logger.info("Visitando método para modificar datos con ID {}", id);
        try {
            Datos datosDto1 = new Datos();
            datosDto1.setIdDato(id);
            datosDto1.setDato(responseDto.getMensaje());
            datosDto1.setIdTipo(1);
            datosRepository.save(datosDto1);
            logger.info("Datos con ID {} modificados exitosamente", id);
        } catch (Exception e) {
            logger.error("Error al modificar datos con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public void visitEliminar(Integer id) {
        logger.info("Visitando método para eliminar datos con ID {}", id);
        try {
            datosRepository.deleteById(Long.valueOf(id));
            logger.info("Datos con ID {} eliminados exitosamente", id);
        } catch (Exception e) {
            logger.error("Error al eliminar datos con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
}
