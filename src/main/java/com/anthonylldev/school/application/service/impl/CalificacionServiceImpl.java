package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.AlumnoDto;
import com.anthonylldev.school.application.dto.CalificacionDto;
import com.anthonylldev.school.application.mapper.CalificacionMapper;
import com.anthonylldev.school.application.service.AlumnoService;
import com.anthonylldev.school.application.service.CalificacionService;
import com.anthonylldev.school.domain.entity.Calificacion;
import com.anthonylldev.school.infrastructure.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionServiceImpl implements CalificacionService {
    private final CalificacionRepository calificacionRepository;
    private final CalificacionMapper calificacionMapper;
    private final AlumnoService alumnoService;

    @Autowired
    public CalificacionServiceImpl(CalificacionRepository calificacionRepository, CalificacionMapper calificacionMapper, AlumnoService alumnoService) {
        this.calificacionRepository = calificacionRepository;
        this.calificacionMapper = calificacionMapper;
        this.alumnoService = alumnoService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CalificacionDto> obtenerCalificacionesDeUnAlumno(Long alumnoId) {
        List<Calificacion> calificacions = this.calificacionRepository.findByAlumno_id(alumnoId);
        return this.calificacionMapper.toDto(calificacions);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CalificacionDto> obtenerCalificacionDeUnAlumno(Long alumnoId, Long calificacionId) {
        return this.calificacionRepository
                .findOneByIdAndAlumno_id(calificacionId, alumnoId)
                .map(this.calificacionMapper::toDto);
    }

    @Override
    @Transactional
    public List<CalificacionDto> crearCalificacionDeAlumno(Long alumnoId, CalificacionDto calificacionDto) {
        calificacionDto.setAlumnoId(alumnoId);
        AlumnoDto alumnoDto = this.alumnoService.añadirCalificacion(alumnoId, calificacionDto);
        return alumnoDto.getCalificaciones();
    }
}
