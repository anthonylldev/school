package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.CuestionarioDto;
import com.anthonylldev.school.domain.entity.Cuestionario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CuestionarioMapper extends EntityMapper<CuestionarioDto, Cuestionario>{
}
