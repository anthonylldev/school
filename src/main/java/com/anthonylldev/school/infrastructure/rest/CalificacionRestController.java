package com.anthonylldev.school.infrastructure.rest;

import com.anthonylldev.school.application.dto.CalificacionDto;
import com.anthonylldev.school.application.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos/{alumnoId}")
public class CalificacionRestController {
    private final CalificacionService calificacionService;

    @Autowired
    public CalificacionRestController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping(value = "/calificaciones", produces = "application/json")
    public ResponseEntity<List<CalificacionDto>> obtenerCalificaciones(@PathVariable Long alumnoId) {
        var calificaciones = this.calificacionService.obtenerCalificacionesDeUnAlumno(alumnoId);
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping(value = "/calificaciones/{calificacionId}", produces = "application/json")
    public ResponseEntity<CalificacionDto> obtenerCalificacion(@PathVariable Long alumnoId, @PathVariable Long calificacionId) {
        return this.calificacionService
                .obtenerCalificacionDeUnAlumno(alumnoId, calificacionId)
                .map(calificacionDto -> new ResponseEntity<>(calificacionDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/calificaciones", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<CalificacionDto>> crearCalificacion(@PathVariable Long alumnoId, @RequestBody CalificacionDto calificacionDto) {
        List<CalificacionDto> calificaciones = this.calificacionService.crearCalificacionDeAlumno(alumnoId, calificacionDto);
        return new ResponseEntity<>(calificaciones, HttpStatus.CREATED);
    }
}
