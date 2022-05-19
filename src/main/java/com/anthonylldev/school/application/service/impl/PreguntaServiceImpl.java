package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.PreguntaDto;
import com.anthonylldev.school.application.mapper.PreguntaMapper;
import com.anthonylldev.school.application.service.PreguntaService;
import com.anthonylldev.school.infrastructure.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {
    private final PreguntaRepository preguntaRepository;
    private final PreguntaMapper preguntaMapper;

    @Autowired
    public PreguntaServiceImpl(PreguntaRepository preguntaRepository, PreguntaMapper preguntaMapper) {
        this.preguntaRepository = preguntaRepository;
        this.preguntaMapper = preguntaMapper;
    }

    @Override
    public List<PreguntaDto> obtenerPreguntas() {
        var preguntas = this.preguntaRepository.findAll();
        return this.preguntaMapper.toDto(preguntas);
    }

    @Override
    public Optional<PreguntaDto> obtenerPreguntaPorId(Long preguntaId) {
        return this.preguntaRepository
                .findById(preguntaId)
                .map(this.preguntaMapper::toDto);
    }

    @Override
    public PreguntaDto crearPregunta(PreguntaDto preguntaDto) {
        var pregunta = this.preguntaMapper.toEntity(preguntaDto);
        pregunta = this.preguntaRepository.save(pregunta);
        return this.preguntaMapper.toDto(pregunta);
    }
}
