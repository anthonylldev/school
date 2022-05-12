package com.anthonylldev.school.infrastructure.rest;

import com.anthonylldev.school.application.dto.LeccionDto;
import com.anthonylldev.school.application.service.LeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos/{cursoId}")
public class LeccionRestController {
    private final LeccionService leccionService;

    @Autowired
    public LeccionRestController(LeccionService leccionService) {
        this.leccionService = leccionService;
    }

    @GetMapping(value = "/lecciones", produces = "application/json")
    public ResponseEntity<List<LeccionDto>> obtenerLeccionesDeCurso(@PathVariable Long cursoId) {
        List<LeccionDto> leccionDtos = this.leccionService.obtenerLeccionesDeUncurso(cursoId);
        return new ResponseEntity<>(leccionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/lecciones/{leccionId}", produces = "application/json")
    public ResponseEntity<LeccionDto> obtenerLeccion(@PathVariable Long cursoId, @PathVariable Long leccionId) {
        return leccionService
                .obtenerLeccionDeUnCurso(cursoId, leccionId)
                .map(leccionDto -> new ResponseEntity<>(leccionDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/lecciones", produces = "application/json", consumes = "application/json")
    public ResponseEntity<LeccionDto> crearLeccionEnCurso(@PathVariable Long cursoId, @RequestBody LeccionDto leccionDto) {
        leccionDto = this.leccionService.crearLeccion(cursoId, leccionDto);
        return new ResponseEntity<>(leccionDto, HttpStatus.CREATED);
    }
}
