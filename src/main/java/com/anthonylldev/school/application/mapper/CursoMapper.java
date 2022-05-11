package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.CursoDto;
import com.anthonylldev.school.domain.entity.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoMapper extends EntityMapper<CursoDto, Curso> {

}
