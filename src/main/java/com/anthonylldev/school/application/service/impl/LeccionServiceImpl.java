package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.LeccionDto;
import com.anthonylldev.school.application.mapper.LeccionMapper;
import com.anthonylldev.school.application.service.LeccionService;
import com.anthonylldev.school.domain.entity.Leccion;
import com.anthonylldev.school.infrastructure.repository.LeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeccionServiceImpl implements LeccionService {
    private final LeccionRepository leccionRepository;
    private final LeccionMapper leccionMapper;

    @Autowired
    public LeccionServiceImpl(LeccionRepository leccionRepository, LeccionMapper leccionMapper) {
        this.leccionRepository = leccionRepository;
        this.leccionMapper = leccionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeccionDto> obtenerLeccionesDeUncurso(Long cursoId) {
        List<Leccion> lecciones = this.leccionRepository.findAllByCurso_Id(cursoId);
        return this.leccionMapper.toDto(lecciones);
    }

    @Override
    public LeccionDto crearLeccion(Long cursoId, LeccionDto leccionDto) {
        leccionDto.setCursoId(cursoId);
        Leccion leccion = this.leccionMapper.toEntity(leccionDto);
        leccion = this.leccionRepository.save(leccion);
        return this.leccionMapper.toDto(leccion);
    }

    @Override
    public Optional<LeccionDto> obtenerLeccionDeUnCurso(Long cursoId, Long leccionId) {
        return this.leccionRepository
                .findOneByIdAndCurso_Id(leccionId, cursoId)
                .map(this.leccionMapper::toDto);
    }
}
