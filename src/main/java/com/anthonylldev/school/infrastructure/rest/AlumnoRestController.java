package com.anthonylldev.school.infrastructure.rest;

import com.anthonylldev.school.application.dto.AlumnoDto;
import com.anthonylldev.school.application.dto.CursoSimpleDto;
import com.anthonylldev.school.application.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlumnoRestController {
    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoRestController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping(value = "/alumnos", produces = "application/json")
    public ResponseEntity<List<AlumnoDto>> obtenerAlumnos() {
        var alumnos = this.alumnoService.obtenerAlumnos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    @GetMapping(value = "/alumnos/{alumnoId}", produces = "application/json")
    public ResponseEntity<AlumnoDto> obtenerAlumno(@PathVariable Long alumnoId) {
        return this.alumnoService
                .obtenerAlumnoPorId(alumnoId)
                .map(alumnoDto -> new ResponseEntity<>(alumnoDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/alumnos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AlumnoDto> crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        alumnoDto = this.alumnoService.crearAlumno(alumnoDto);
        return new ResponseEntity<>(alumnoDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/alumnos/{alumnoId}")
    public ResponseEntity<Void> eliminarAlumnoPorId(@PathVariable Long alumnoId) {
        this.alumnoService.eliminarAlumnoPorId(alumnoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/alumnos/{alumnoId}/cursos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<CursoSimpleDto>> registrarAlumnoEnCurso(@PathVariable Long alumnoId, @RequestBody CursoSimpleDto cursoSimpleDto) {
        List<CursoSimpleDto> cursoSimpleDtos = this.alumnoService.registrarAlumnoEnCurso(alumnoId, cursoSimpleDto);
        return new ResponseEntity<>(cursoSimpleDtos, HttpStatus.OK);
    }

    @DeleteMapping(value = "/alumnos/{alumnoId}/cursos/{cursoId}")
    public ResponseEntity<Void> eliminarCursoDeAlumno(@PathVariable Long alumnoId, @PathVariable Long cursoId) {
        this.alumnoService.eliminarCursoDeAlumno(alumnoId, cursoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
