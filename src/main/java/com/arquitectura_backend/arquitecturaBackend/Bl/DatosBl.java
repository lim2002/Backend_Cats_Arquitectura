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
    //Guardar los datos (Solo el admin puede guardar)
    public void guardar(ResponseDto responseDto) {
        Datos datosDto1 = new Datos();
        datosDto1.setDato(responseDto.getMensaje());
        datosDto1.setIdTipo(1);
        System.out.println("datosDto1: " + datosDto1);
        datosRepository.save(datosDto1);

    }
    //Modificar los datos (Solo el admin)
    public void modificar(ResponseDto responseDto, Integer id) {
        Datos datosDto1 = new Datos();
        datosDto1.setIdDato(id);
        datosDto1.setDato(responseDto.getMensaje());
        datosDto1.setIdTipo(1);
        datosRepository.save(datosDto1);
    }
    //Eliminar los datos (Solo el admin)
    public void eliminar(Integer id) {
        datosRepository.deleteById(Long.valueOf(id));
    }
}
