package com.anthonylldev.school.application.service.impl;

import com.anthonylldev.school.application.dto.CursoDto;
import com.anthonylldev.school.application.mapper.CursoMapper;
import com.anthonylldev.school.application.service.CursoService;
import com.anthonylldev.school.domain.entity.Curso;
import com.anthonylldev.school.infrastructure.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDto> obtenerCursos() {
        List<Curso> cursos = this.cursoRepository.findAll();
        return this.cursoMapper.toDto(cursos);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CursoDto> obtenerCursoPorId(Long id) {
        return this.cursoRepository
                .findById(id)
                .map(curso -> this.cursoMapper.toDto(curso));
    }

    @Override
    @Transactional
    public CursoDto crearCurso(CursoDto cursoDto) {
        Curso curso = this.cursoMapper.toEntity(cursoDto);
        curso = this.cursoRepository.save(curso);
        return this.cursoMapper.toDto(curso);
    }

    @Override
    @Transactional
    public void eliminarCurso(Long id) {
        this.cursoRepository.deleteById(id);
    }
}
