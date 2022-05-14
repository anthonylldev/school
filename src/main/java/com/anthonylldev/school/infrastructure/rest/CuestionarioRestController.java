package com.anthonylldev.school.infrastructure.rest;

import com.anthonylldev.school.application.dto.CuestionarioDto;
import com.anthonylldev.school.application.service.CuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuestionarioRestController {
    private CuestionarioService cuestionarioService;

    @Autowired
    public CuestionarioRestController(CuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
    }

    @GetMapping(value = "/cuestionarios", produces = "application/json")
    public ResponseEntity<List<CuestionarioDto>> obtenerCuestionarios() {
        var cuestionarios = this.cuestionarioService.obtenerCuestionarios();
        return new ResponseEntity<>(cuestionarios, HttpStatus.OK);
    }

    @GetMapping(value = "/cuestionarios/{cuestionarioId}", produces = "application/json")
    public ResponseEntity<CuestionarioDto> obtenerCuestionarioPorId(@PathVariable Long cuestionarioId) {
        return this.cuestionarioService
                .obtenerCuestionarioPorId(cuestionarioId)
                .map(cuestionarioDto -> new ResponseEntity<>(cuestionarioDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/cuestionarios", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CuestionarioDto> crearCuestionario(@RequestBody CuestionarioDto cuestionarioDto) {
        cuestionarioDto = this.cuestionarioService.crearCuestionario(cuestionarioDto);
        return new ResponseEntity<>(cuestionarioDto, HttpStatus.CREATED);
    }
}
