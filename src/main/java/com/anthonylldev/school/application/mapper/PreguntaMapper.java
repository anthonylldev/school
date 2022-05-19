package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.PreguntaDto;
import com.anthonylldev.school.domain.entity.Pregunta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { PreguntaCuestionarioMapper.class })
public interface PreguntaMapper extends EntityMapper<PreguntaDto, Pregunta> {

    default Pregunta fromId(Long id) {
        if (id == null) return null;
        Pregunta pregunta = new Pregunta();
        pregunta.setId(id);
        return pregunta;
    }
}
