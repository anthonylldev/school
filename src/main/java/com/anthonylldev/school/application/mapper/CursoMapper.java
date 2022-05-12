package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.CursoDto;
import com.anthonylldev.school.domain.entity.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { LeccionMapper.class })
public interface CursoMapper extends EntityMapper<CursoDto, Curso> {

    default Curso fromId(Long id) {
        if (id == null) {
            return null;
        } else {
            Curso curso = new Curso();
            curso.setId(id);
            return curso;
        }
    }
}
