package com.distribuida.dto;

import java.util.List;

public class ClientDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    private List<OrdenDto> ordens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<OrdenDto> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<OrdenDto> ordens) {
        this.ordens = ordens;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ordens=" + ordens +
                '}';
    }
}
