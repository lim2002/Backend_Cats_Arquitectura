package com.arquitectura_backend.arquitecturaBackend.Bl;

import com.arquitectura_backend.arquitecturaBackend.Dao.DatosRepository;
import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;
import com.arquitectura_backend.arquitecturaBackend.Entity.Datos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosVisitorImpl implements DatosVisitor{
    @Autowired
    private DatosRepository datosRepository;

    @Override
    public void visitGuardar(ResponseDto responseDto) {
        // Tu lógica para guardar
        Datos datosDto1 = new Datos();
        datosDto1.setDato(responseDto.getMensaje());
        datosDto1.setIdTipo(1);
        System.out.println("datosDto1: " + datosDto1);
        datosRepository.save(datosDto1);

    }

    @Override
    public void visitModificar(ResponseDto responseDto, Integer id) {
        // Tu lógica para modificar
        Datos datosDto1 = new Datos();
        datosDto1.setIdDato(id);
        datosDto1.setDato(responseDto.getMensaje());
        datosDto1.setIdTipo(1);
        datosRepository.save(datosDto1);
    }

    @Override
    public void visitEliminar(Integer id) {
        // Tu lógica para eliminar
        datosRepository.deleteById(Long.valueOf(id));
    }
}
