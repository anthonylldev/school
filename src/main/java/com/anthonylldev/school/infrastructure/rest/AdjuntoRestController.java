package com.anthonylldev.school.infrastructure.rest;

import com.anthonylldev.school.application.dto.AdjuntoDto;
import com.anthonylldev.school.application.service.AdjuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdjuntoRestController {
    private final AdjuntoService adjuntoService;

    @Autowired
    public AdjuntoRestController(AdjuntoService adjuntoService) {
        this.adjuntoService = adjuntoService;
    }

    @GetMapping(value = "/adjuntos", produces = "application/json")
    public ResponseEntity<List<AdjuntoDto>> obtenerAdjuntos() {
        var adjuntos = this.adjuntoService.obtenerAdjuntos();
        return new ResponseEntity<>(adjuntos, HttpStatus.OK);
    }

    @GetMapping(value = "/adjuntos/{adjuntoId}", produces = "application/json")
    public ResponseEntity<AdjuntoDto> obtenerAdjunto(@PathVariable Long adjuntoId) {
        return this.adjuntoService
                .obtenerAdjuntoPorId(adjuntoId)
                .map(adjuntoDto -> new ResponseEntity<>(adjuntoDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/adjuntos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AdjuntoDto> crearAdjunto(@RequestBody AdjuntoDto adjuntoDto) {
        adjuntoDto = this.adjuntoService.crearAdjunto(adjuntoDto);
        return new ResponseEntity<>(adjuntoDto, HttpStatus.CREATED);
    }
}
