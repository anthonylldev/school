package com.anthonylldev.school.infrastructure.repository;

import com.anthonylldev.school.domain.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
