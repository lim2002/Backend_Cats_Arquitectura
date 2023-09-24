package com.arquitectura_backend.arquitecturaBackend.Bl;

import com.arquitectura_backend.arquitecturaBackend.Dao.DatosRepository;
import com.arquitectura_backend.arquitecturaBackend.Dto.DatosDto;
import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;
import com.arquitectura_backend.arquitecturaBackend.Entity.Datos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatosBl {

    @Autowired
    private DatosRepository datosRepository;

    @Autowired
    private DatosVisitor datosVisitor;

    //Mostar al usuario y admin todos los datos curiosos de los gatos


    public Page<DatosDto> getall(Pageable pageable) {
        Page<Datos> datosPage = datosRepository.findAll(pageable);
        return datosPage.map(dato -> {
            DatosDto datosDto = new DatosDto();
            datosDto.setIdDato(dato.getIdDato());
            datosDto.setDato(dato.getDato());
            datosDto.setIdTipo(dato.getIdTipo());
            return datosDto;
        });
    }



    //Guardar los datos (Solo el admin )
    public ResponseDto guardar(ResponseDto responseDto) {
        datosVisitor.visitGuardar(responseDto);
        return new ResponseDto("Datos guardados exitosamente");
    }
    //Modificar los datos (Solo el admin)
    public ResponseDto modificar(ResponseDto responseDto, Integer id) {
        datosVisitor.visitModificar(responseDto, id);
        return new ResponseDto("Datos modificados exitosamente");
    }
    //Eliminar los datos (Solo el admin)
    public ResponseDto eliminar(Integer id) {
        datosVisitor.visitEliminar(id);
        return new ResponseDto("Datos eliminados exitosamente");
    }


}
