package com.arquitectura_backend.arquitecturaBackend.Bl;

import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;

public interface DatosVisitor {
    void visitGuardar(ResponseDto responseDto);
    void visitModificar(ResponseDto responseDto, Integer id);
    void visitEliminar(Integer id);
}
