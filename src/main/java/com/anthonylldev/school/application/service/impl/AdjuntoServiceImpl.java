package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.AdjuntoDto;
import com.anthonylldev.school.application.mapper.AdjuntoMapper;
import com.anthonylldev.school.application.service.AdjuntoService;
import com.anthonylldev.school.domain.entity.Adjunto;
import com.anthonylldev.school.infrastructure.repository.AdjuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdjuntoServiceImpl implements AdjuntoService {
    private final AdjuntoRepository adjuntoRepository;
    private final AdjuntoMapper adjuntoMapper;

    @Autowired
    public AdjuntoServiceImpl(AdjuntoRepository adjuntoRepository, AdjuntoMapper adjuntoMapper) {
        this.adjuntoRepository = adjuntoRepository;
        this.adjuntoMapper = adjuntoMapper;
    }

    @Override
    public List<AdjuntoDto> obtenerAdjuntos() {
        return this.adjuntoMapper.toDto(this.adjuntoRepository.findAll());
    }

    @Override
    public Optional<AdjuntoDto> obtenerAdjuntoPorId(Long id) {
        return this.adjuntoRepository
                .findById(id)
                .map(this.adjuntoMapper::toDto);
    }

    @Override
    public AdjuntoDto crearAdjunto(AdjuntoDto adjuntoDto) {
        Adjunto adjunto = this.adjuntoMapper.toEntity(adjuntoDto);
        adjunto = this.adjuntoRepository.save(adjunto);
        return this.adjuntoMapper.toDto(adjunto);
    }
}
