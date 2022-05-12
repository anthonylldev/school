package com.anthonylldev.school.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scool_sequence")
    @SequenceGenerator(name = "school_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "resumen", nullable = false, length = 2000)
    private String resumen;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    Set<Leccion> lecciones;

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

    public Set<Leccion> getLecciones() {
        return lecciones;
    }

    public void setLecciones(Set<Leccion> lecciones) {
        this.lecciones = lecciones;
        this.lecciones.forEach(leccion -> leccion.setCurso(this));
    }
}
