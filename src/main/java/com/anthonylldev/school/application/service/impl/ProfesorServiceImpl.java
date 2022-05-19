package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.ProfesorDto;
import com.anthonylldev.school.application.mapper.ProfesorMapper;
import com.anthonylldev.school.application.service.ProfesorService;
import com.anthonylldev.school.domain.entity.Profesor;
import com.anthonylldev.school.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }

    @Override
    public List<ProfesorDto> obtenerProfesores() {
        var profesores = this.profesorRepository.findAll();
        return this.profesorMapper.toDto(profesores);
    }

    @Override
    public Optional<ProfesorDto> obtenerProfesorPorId(Long profesorId) {
        return this.profesorRepository
                .findById(profesorId)
                .map(this.profesorMapper::toDto);
    }

    @Override
    public ProfesorDto crearProfesor(ProfesorDto profesorDto) {
        var profesor = profesorMapper.toEntity(profesorDto);
        profesor = this.profesorRepository.save(profesor);
        return this.profesorMapper.toDto(profesor);
    }
}
