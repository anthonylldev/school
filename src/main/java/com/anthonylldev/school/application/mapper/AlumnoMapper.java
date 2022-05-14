package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.AlumnoDto;
import com.anthonylldev.school.application.dto.AlumnoSimpleDto;
import com.anthonylldev.school.domain.entity.Alumno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CursoMapper.class })
public interface AlumnoMapper extends EntityMapper<AlumnoDto, Alumno>{

    @Mapping(target = "url", ignore = true)
    @Mapping(target =  "cursos", ignore = true)
    Alumno toEntity(AlumnoSimpleDto alumnoSimpleDto);
    AlumnoSimpleDto toSimpleDto(Alumno alumno);
}
