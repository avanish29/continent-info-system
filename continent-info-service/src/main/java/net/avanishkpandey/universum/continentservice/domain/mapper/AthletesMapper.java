package net.avanishkpandey.universum.continentservice.domain.mapper;

import net.avanishkpandey.universum.continentservice.domain.dto.AthleteResponse;
import net.avanishkpandey.universum.continentservice.domain.model.Athlete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AthletesMapper {
    AthletesMapper INSTANCE = Mappers.getMapper(AthletesMapper.class);

    @Mapping(target = "athlete", source = "fullName")
    @Mapping(target = "date", source = "statDate")
    @Mapping(target = "total", expression = "java(entity.getGold() + entity.getSilver() + entity.getBronze())")
    AthleteResponse toModel(final Athlete entity);
}
