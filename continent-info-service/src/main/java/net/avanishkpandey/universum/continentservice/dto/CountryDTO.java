package net.avanishkpandey.universum.continentservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.entity.Country;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CountryDTO implements Serializable {
    private Long id;
    private String name;
    private String region;
    private String continent;
    private BigDecimal area;
    private LocalDate nationalDay;
    private String alpha2Code;
    private String alpha3Code;
    private Set<CountryLanguageDTO> languages;
    private Set<CountryStatsDTO> statistics;

    public static CountryDTO fromEntity(final Country entity) {
        return CountryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .region(entity.getRegion().getName())
                .area(entity.getArea())
                .alpha2Code(entity.getAlpha2Code())
                .continent(entity.getRegion().getContinent().getName())
                .alpha3Code(entity.getAlpha3Code()).nationalDay(entity.getNationalDay())
                .languages(Optional.of(entity.getCountryLanguages())
                        .orElseGet(Collections::emptySet).stream()
                        .map(CountryLanguageDTO::fromEntity)
                        .collect(Collectors.toSet()))
                .statistics(Optional.of(entity.getStatistics())
                        .orElseGet(Collections::emptySet).stream()
                        .map(CountryStatsDTO::fromEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
