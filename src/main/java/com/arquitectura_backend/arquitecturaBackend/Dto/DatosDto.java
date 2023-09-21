package com.arquitectura_backend.arquitecturaBackend.Dto;

public class DatosDto {

    private Integer idDato;
    private String dato;
    private Integer idTipo;

    public DatosDto() {
    }

    public DatosDto(Integer idDato, String dato, Integer idTipo) {
        this.idDato = idDato;
        this.dato = dato;
        this.idTipo = idTipo;
    }

    public Integer getIdDato() {
        return idDato;
    }

    public void setIdDato(Integer idDato) {
        this.idDato = idDato;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public String toString() {
        return "DatosDto{" +
                "idDato=" + idDato +
                ", dato='" + dato + '\'' +
                ", idTipo=" + idTipo +
                '}';
    }
}
