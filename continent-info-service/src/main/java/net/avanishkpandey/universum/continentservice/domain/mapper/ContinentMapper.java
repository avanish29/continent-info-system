package net.avanishkpandey.universum.continentservice.domain.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import net.avanishkpandey.universum.continentservice.domain.dto.ContinentResponse;
import net.avanishkpandey.universum.continentservice.domain.model.Continent;
import net.avanishkpandey.universum.continentservice.web.controller.ContinentController;
import org.mapstruct.Mapping;

@Mapper
public interface ContinentMapper {
    ContinentMapper INSTANCE = Mappers.getMapper(ContinentMapper.class);

    @Mapping(target = "add", ignore = true)
	ContinentResponse toModel(Continent continentEntity);

    @AfterMapping
    default void addLinks(@MappingTarget ContinentResponse responseModel, Continent entity) {
        responseModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContinentController.class).findById(entity.getId())).withSelfRel());
        responseModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContinentController.class).findAllRegionsByContinent(entity.getId())).withRel("regions"));
    }
}
