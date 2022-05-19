package com.anthonylldev.school.infrastructure.rest;

import com.anthonylldev.school.application.dto.CursoDto;
import com.anthonylldev.school.application.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursoRestController {
    private final CursoService cursoService;

    @Autowired
    public CursoRestController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping(value = "/cursos", produces = "application/json")
    public ResponseEntity<List<CursoDto>> obtenerCursos() {
        List<CursoDto> cursoDtos = this.cursoService.obtenerCursos();
        return new ResponseEntity<>(cursoDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/cursos/{cursoId}", produces = "application/json")
    public ResponseEntity<CursoDto> obtenerCursoPorId(@PathVariable Long cursoId) {
        return this.cursoService
                .obtenerCursoPorId(cursoId)
                .map(cursoDto -> new ResponseEntity<>(cursoDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/cursos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CursoDto> crearCurso(@RequestBody CursoDto cursoDto) {
        cursoDto = this.cursoService.crearCurso(cursoDto);
        return new ResponseEntity<>(cursoDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cursos/{cursoId}")
    public ResponseEntity<Void> eliminarCursoPorId(@PathVariable Long cursoId) {
        this.cursoService.eliminarCurso(cursoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
