package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.AlumnoDto;
import com.anthonylldev.school.application.dto.CursoSimpleDto;
import com.anthonylldev.school.application.mapper.AlumnoMapper;
import com.anthonylldev.school.application.service.AlumnoService;
import com.anthonylldev.school.domain.entity.Alumno;
import com.anthonylldev.school.infrastructure.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, AlumnoMapper alumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoDto> obtenerAlumnos() {
        var alumnos = this.alumnoRepository.findAll();
        return this.alumnoMapper.toDto(alumnos);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoDto> obtenerAlumnoPorId(Long id) {
        return this.alumnoRepository
                .findById(id)
                .map(this.alumnoMapper::toDto);
    }

    @Override
    @Transactional
    public AlumnoDto crearAlumno(AlumnoDto alumnoDto) {
        var alumno = this.alumnoMapper.toEntity(alumnoDto);
        alumno = this.alumnoRepository.save(alumno);
        return this.alumnoMapper.toDto(alumno);
    }

    @Override
    public void eliminarAlumnoPorId(Long alumnoId) {
        this.alumnoRepository.deleteById(alumnoId);
    }

    @Override
    @Transactional
    public List<CursoSimpleDto> registrarAlumnoEnCurso(Long alumnoId, CursoSimpleDto cursoSimpleDto) {
        AlumnoDto alumnoDto = obtenerAlumnoPorId(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no existe"));

        alumnoDto.getCursos().add(cursoSimpleDto);
        Alumno alumno = this.alumnoRepository.save(this.alumnoMapper.toEntity(alumnoDto));
        alumnoDto = this.alumnoMapper.toDto(alumno);
        return alumnoDto.getCursos();
    }

    @Override
    @Transactional
    public void eliminarCursoDeAlumno(Long alumnoId, Long cursoId) {
        Alumno alumno = this.alumnoRepository
                .findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no existe"));

        alumno.eliminarCursoPorId(cursoId);
        this.alumnoRepository.save(alumno);
    }
}
