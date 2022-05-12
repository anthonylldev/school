package com.anthonylldev.school.application.service;

import com.anthonylldev.school.application.dto.LeccionDto;

import java.util.List;
import java.util.Optional;

public interface LeccionService {
    List<LeccionDto> obtenerLeccionesDeUncurso(Long cursoId);
    LeccionDto crearLeccion(Long cursoId, LeccionDto leccionDto);
    Optional<LeccionDto> obtenerLeccionDeUnCurso(Long cursoId, Long leccionId);
}
