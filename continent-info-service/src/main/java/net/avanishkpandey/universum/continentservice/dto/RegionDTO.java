package net.avanishkpandey.universum.continentservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.entity.Region;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegionDTO implements Serializable {
    private Long id;
    private String name;
    private BigDecimal area;
    private String continent;
    private List<CountryDTO> countries;

    public static RegionDTO fromEntity(final Region entity) {
        return RegionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .area(entity.getRegionArea())
                .continent(entity.getContinent().getName())
                .countries(Optional.of(entity.getCountries())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(CountryDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
