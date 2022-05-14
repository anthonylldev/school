package com.anthonylldev.school.application.service;

import com.anthonylldev.school.application.dto.PreguntaDto;

import java.util.List;
import java.util.Optional;

public interface PreguntaService {
    List<PreguntaDto> obtenerPreguntas();
    Optional<PreguntaDto> obtenerPreguntaPorId(Long preguntaId);
    PreguntaDto crearPregunta(PreguntaDto preguntaDto);
}
