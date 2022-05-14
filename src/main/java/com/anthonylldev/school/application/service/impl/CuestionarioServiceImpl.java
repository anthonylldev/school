package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.CuestionarioDto;
import com.anthonylldev.school.application.mapper.CuestionarioMapper;
import com.anthonylldev.school.application.service.CuestionarioService;
import com.anthonylldev.school.domain.entity.Cuestionario;
import com.anthonylldev.school.infrastructure.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuestionarioServiceImpl implements CuestionarioService {
    private CuestionarioRepository cuestionarioRepository;
    private CuestionarioMapper cuestionarioMapper;

    @Autowired
    public CuestionarioServiceImpl(CuestionarioRepository cuestionarioRepository, CuestionarioMapper cuestionarioMapper) {
        this.cuestionarioRepository = cuestionarioRepository;
        this.cuestionarioMapper = cuestionarioMapper;
    }

    @Override
    public List<CuestionarioDto> obtenerCuestionarios() {
        return this.cuestionarioMapper.toDto(this.cuestionarioRepository.findAll());
    }

    @Override
    public Optional<CuestionarioDto> obtenerCuestionarioPorId(Long cuestionarioId) {
        return this.cuestionarioRepository
                .findById(cuestionarioId)
                .map(this.cuestionarioMapper::toDto);
    }

    @Override
    public CuestionarioDto crearCuestionario(CuestionarioDto cuestionarioDto) {
        var cuestionario = this.cuestionarioMapper.toEntity(cuestionarioDto);
        cuestionario = this.cuestionarioRepository.save(cuestionario);
        return this.cuestionarioMapper.toDto(cuestionario);
    }
}
