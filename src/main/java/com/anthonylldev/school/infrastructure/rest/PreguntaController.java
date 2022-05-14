package com.anthonylldev.school.infrastructure.rest;

import com.anthonylldev.school.application.dto.PreguntaDto;
import com.anthonylldev.school.application.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PreguntaController {
    private PreguntaService preguntaService;

    @Autowired
    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping(value = "/preguntas", produces = "application/json")
    public ResponseEntity<List<PreguntaDto>> obtenerPreguntas() {
        var preguntas = this.preguntaService.obtenerPreguntas();
        return new ResponseEntity<>(preguntas, HttpStatus.OK);
    }

    @GetMapping(value = "/preguntas/{preguntaId}", produces = "application/json")
    public ResponseEntity<PreguntaDto> obtenerPreguntaPorId(@PathVariable Long preguntaId) {
        return this.preguntaService
                .obtenerPreguntaPorId(preguntaId)
                .map(preguntaDto -> new ResponseEntity<>(preguntaDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/preguntas", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PreguntaDto> crearPregunta(@RequestBody PreguntaDto preguntaDto) {
        preguntaDto = this.preguntaService.crearPregunta(preguntaDto);
        return new ResponseEntity<>(preguntaDto, HttpStatus.CREATED);
    }
}
