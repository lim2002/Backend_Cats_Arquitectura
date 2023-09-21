package com.arquitectura_backend.arquitecturaBackend.Bl;

import com.arquitectura_backend.arquitecturaBackend.Dao.DatosRepository;
import com.arquitectura_backend.arquitecturaBackend.Dto.DatosDto;
import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;
import com.arquitectura_backend.arquitecturaBackend.Entity.Datos;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DatosDto> getall() {
        List<Datos> datos = datosRepository.findAll();
        System.out.println("datos: " + datos);
        List<DatosDto> datosDtos = new ArrayList<>();
        for (Datos dato : datos) {
            DatosDto datosDto = new DatosDto();
            datosDto.setIdDato(dato.getIdDato());
            datosDto.setDato(dato.getDato());
            datosDto.setIdTipo(dato.getIdTipo());
            datosDtos.add(datosDto);
        }
        System.out.println("datosDtos: " + datosDtos);
        return datosDtos;
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
