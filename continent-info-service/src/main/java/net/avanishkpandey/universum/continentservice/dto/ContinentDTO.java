package net.avanishkpandey.universum.continentservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.entity.Continent;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContinentDTO implements Serializable {
    private Long id;
    private String name;
    private List<RegionDTO> regions;

    public static ContinentDTO fromEntity(final Continent entity) {
        return ContinentDTO.builder()
                .id(entity.getId()).name(entity.getName())
                .regions(Optional.of(entity.getRegions())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(RegionDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
