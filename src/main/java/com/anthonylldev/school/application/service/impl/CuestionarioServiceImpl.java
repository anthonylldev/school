package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.CuestionarioDto;
import com.anthonylldev.school.application.dto.PreguntaCuestionarioDto;
import com.anthonylldev.school.application.mapper.CuestionarioMapper;
import com.anthonylldev.school.application.service.CuestionarioService;
import com.anthonylldev.school.domain.entity.Cuestionario;
import com.anthonylldev.school.infrastructure.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CuestionarioServiceImpl implements CuestionarioService {
    private final CuestionarioRepository cuestionarioRepository;
    private final CuestionarioMapper cuestionarioMapper;

    @Autowired
    public CuestionarioServiceImpl(CuestionarioRepository cuestionarioRepository, CuestionarioMapper cuestionarioMapper) {
        this.cuestionarioRepository = cuestionarioRepository;
        this.cuestionarioMapper = cuestionarioMapper;

    }

    @Override
    @Transactional(readOnly = true)
    public List<CuestionarioDto> obtenerCuestionarios() {
        List<Cuestionario> cuestionarios = this.cuestionarioRepository.findAll();
        return this.cuestionarioMapper.toDto(cuestionarios);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CuestionarioDto> obtenerCuestionarioPorId(Long cuestionarioId) {
        return this.cuestionarioRepository
                .findById(cuestionarioId)
                .map(this.cuestionarioMapper::toDto);
    }

    @Override
    @Transactional
    public CuestionarioDto crearCuestionario(CuestionarioDto cuestionarioDto) {
        var cuestionario = this.cuestionarioMapper.toEntity(cuestionarioDto);
        cuestionario = this.cuestionarioRepository.save(cuestionario);
        return this.cuestionarioMapper.toDto(cuestionario);
    }

    @Override
    @Transactional
    public List<PreguntaCuestionarioDto> añadePreguntaEnCuestionario(Long cuestionarioId, PreguntaCuestionarioDto preguntaCuestionarioDto) {
        CuestionarioDto cuestionarioDto = obtenerCuestionarioPorId(cuestionarioId)
                .orElseThrow(() -> new RuntimeException("Cuestionario no existe"));

        preguntaCuestionarioDto.setCuestionarioId(cuestionarioId);
        cuestionarioDto.getPreguntas().add(preguntaCuestionarioDto);
        Cuestionario cuestionario = this.cuestionarioRepository.save(this.cuestionarioMapper.toEntity(cuestionarioDto));
        cuestionarioDto = this.cuestionarioMapper.toDto(cuestionario);

        return cuestionarioDto.getPreguntas();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreguntaCuestionarioDto> obtenerPreguntasDeCuestionario(Long cuestionarioId) {
        CuestionarioDto cuestionarioDto = obtenerCuestionarioPorId(cuestionarioId)
                .orElseThrow(() -> new RuntimeException("Cuestionario no existe"));

        return cuestionarioDto.getPreguntas();
    }
}
