package com.anthonylldev.school.application.service;

import com.anthonylldev.school.application.dto.CursoDto;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<CursoDto> obtenerCursos();
    Optional<CursoDto> obtenerCursoPorId(Long id);
    CursoDto crearCurso(CursoDto cursoDto);
    void eliminarCurso(Long id);
}
