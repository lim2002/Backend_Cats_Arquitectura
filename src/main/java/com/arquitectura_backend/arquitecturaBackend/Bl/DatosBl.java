package com.arquitectura_backend.arquitecturaBackend.Bl;

import com.arquitectura_backend.arquitecturaBackend.Dao.DatosRepository;
import com.arquitectura_backend.arquitecturaBackend.Dto.DatosDto;
import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;
import com.arquitectura_backend.arquitecturaBackend.Entity.Datos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatosBl {

    private static final Logger logger = LoggerFactory.getLogger(DatosBl.class);

    @Autowired
    private DatosRepository datosRepository;

    @Autowired
    private DatosVisitor datosVisitor;

    //Mostar al usuario y admin todos los datos curiosos de los gatos


    public Page<DatosDto> getall(Pageable pageable) {
        logger.info("Solicitud para obtener todos los datos curiosos recibida");
        try {
            Page<Datos> datosPage = datosRepository.findAll(pageable);
            logger.info("Datos curiosos obtenidos exitosamente");
            return datosPage.map(dato -> {
                DatosDto datosDto = new DatosDto();
                datosDto.setIdDato(dato.getIdDato());
                datosDto.setDato(dato.getDato());
                datosDto.setIdTipo(dato.getIdTipo());
                return datosDto;
            });
        } catch (Exception e) {
            logger.error("Error al obtener todos los datos curiosos: {}", e.getMessage());
            throw e;
        }
    }

    public ResponseDto guardar(ResponseDto responseDto) {
        logger.info("Solicitud para guardar datos curiosos recibida");
        try {
            datosVisitor.visitGuardar(responseDto);
            logger.info("Datos curiosos guardados exitosamente");
            return new ResponseDto("Datos guardados exitosamente");
        } catch (Exception e) {
            logger.error("Error al guardar datos curiosos: {}", e.getMessage());
            throw e;
        }
    }

    public ResponseDto modificar(ResponseDto responseDto, Integer id) {
        logger.info("Solicitud para modificar datos curiosos con ID {} recibida", id);
        try {
            datosVisitor.visitModificar(responseDto, id);
            logger.info("Datos curiosos con ID {} modificados exitosamente", id);
            return new ResponseDto("Datos modificados exitosamente");
        } catch (Exception e) {
            logger.error("Error al modificar datos curiosos con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    public ResponseDto eliminar(Integer id) {
        logger.info("Solicitud para eliminar datos curiosos con ID {} recibida", id);
        try {
            datosVisitor.visitEliminar(id);
            logger.info("Datos curiosos con ID {} eliminados exitosamente", id);
            return new ResponseDto("Datos eliminados exitosamente");
        } catch (Exception e) {
            logger.error("Error al eliminar datos curiosos con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }


}
