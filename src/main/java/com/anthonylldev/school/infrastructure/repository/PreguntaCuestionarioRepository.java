package com.anthonylldev.school.infrastructure.repository;

import com.anthonylldev.school.domain.entity.PreguntaCuestionario;
import com.anthonylldev.school.domain.key.PreguntaCuestionarioKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaCuestionarioRepository extends JpaRepository<PreguntaCuestionario, Long> {
}
