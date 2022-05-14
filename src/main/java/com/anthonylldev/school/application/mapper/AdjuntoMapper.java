package com.anthonylldev.school.application.mapper;

import com.anthonylldev.school.application.dto.AdjuntoDto;
import com.anthonylldev.school.domain.entity.Adjunto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdjuntoMapper extends EntityMapper<AdjuntoDto, Adjunto>{
}
