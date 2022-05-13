package com.anthonylldev.school.infrastructure.repository;

import com.anthonylldev.school.domain.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
