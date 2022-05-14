package com.anthonylldev.school.infrastructure.repository;

import com.anthonylldev.school.domain.entity.Cuestionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuestionarioRepository extends JpaRepository<Cuestionario, Long> {
}