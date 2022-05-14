package com.anthonylldev.school.infrastructure.repository;

import com.anthonylldev.school.domain.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
}