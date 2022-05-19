package com.anthonylldev.school.infrastructure.repository;

import com.anthonylldev.school.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
