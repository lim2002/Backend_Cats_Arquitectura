package com.arquitectura_backend.arquitecturaBackend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "datos")
public class Datos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dato", nullable = false)
    private Integer idDato;

    @Column(name = "dato", nullable = false)
    private String dato;

    @Column(name = "id_tipo", nullable = false)
    private Integer idTipo;

    public Datos() {
    }

    public Datos(Integer idDato, String dato, Integer idTipo) {
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
        return "Datos{" +
                "idDato=" + idDato +
                ", dato='" + dato + '\'' +
                ", idTipo=" + idTipo +
                '}';
    }
}
