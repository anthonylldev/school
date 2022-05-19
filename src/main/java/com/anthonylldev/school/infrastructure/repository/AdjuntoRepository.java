package com.anthonylldev.school.infrastructure.repository;

import com.anthonylldev.school.domain.entity.Adjunto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdjuntoRepository extends JpaRepository<Adjunto, Long> {
}