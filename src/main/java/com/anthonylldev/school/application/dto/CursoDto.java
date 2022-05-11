package com.anthonylldev.school.application.dto;

import java.io.Serializable;
import java.util.Objects;

public class CursoDto implements Serializable {
    private Long id;
    private String nombre;
    private String resumen;

    public CursoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CursoDto cursoDto = (CursoDto) obj;
        return Objects.equals(id, cursoDto.id) && Objects.equals(nombre, cursoDto.nombre) && Objects.equals(resumen, cursoDto.resumen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, resumen);
    }

    @Override
    public String toString() {
        return "CursoDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}