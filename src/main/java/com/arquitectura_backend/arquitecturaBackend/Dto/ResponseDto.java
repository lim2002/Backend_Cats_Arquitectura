package com.arquitectura_backend.arquitecturaBackend.Dto;

public class ResponseDto {
    private String mensaje;

    public ResponseDto() {
    }

    public ResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "mensaje='" + mensaje + '\'' +
                '}';
    }
}
