package net.avanishkpandey.universum.continentservice.dto;

import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.entity.CountryStats;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class CountryStatsDTO implements Serializable {
    private Integer year;
    private Long population;
    private BigDecimal gdp;

    public static CountryStatsDTO fromEntity(final CountryStats entity) {
        return CountryStatsDTO.builder()
                .year(entity.getPk().getYear()).population(entity.getPopulation())
                .gdp(entity.getGdp()).build();
    }
}
