package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.CursoDto;
import com.anthonylldev.school.application.dto.CursoSimpleDto;
import com.anthonylldev.school.domain.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { LeccionMapper.class, AlumnoMapper.class })
public interface CursoMapper extends EntityMapper<CursoDto, Curso> {

    @Mapping(target = "resumen", ignore = true)
    @Mapping(target = "lecciones", ignore = true)
    @Mapping(target = "alumnos", ignore = true)
    Curso toEntity(CursoSimpleDto cursoSimpleDto);
    CursoSimpleDto toSimpleDto(Curso curso);

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
