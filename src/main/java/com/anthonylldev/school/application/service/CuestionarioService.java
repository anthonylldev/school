package com.anthonylldev.school.application.service;

import com.anthonylldev.school.application.dto.CuestionarioDto;

import java.util.List;
import java.util.Optional;

public interface CuestionarioService {
    List<CuestionarioDto> obtenerCuestionarios();
    Optional<CuestionarioDto> obtenerCuestionarioPorId(Long cuestionarioId);
    CuestionarioDto crearCuestionario(CuestionarioDto cuestionarioDto);
}
