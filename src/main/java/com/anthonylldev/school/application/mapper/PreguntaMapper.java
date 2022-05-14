package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.PreguntaDto;
import com.anthonylldev.school.domain.entity.Pregunta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreguntaMapper extends EntityMapper<PreguntaDto, Pregunta> {
}
