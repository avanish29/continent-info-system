package net.avanishkpandey.universum.continentservice.dto;

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
public class ContinentDTO implements Serializable {
    private Long id;
    private String name;
    private List<RegionDTO> regions;

    public static ContinentDTO fromEntity(final Continent entity) {
        return ContinentDTO.builder()
                .id(entity.getId()).name(entity.getName())
                .regions(Optional.ofNullable(entity.getRegions()).orElseGet(Collections::emptyList).stream()
                        .map(RegionDTO::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
