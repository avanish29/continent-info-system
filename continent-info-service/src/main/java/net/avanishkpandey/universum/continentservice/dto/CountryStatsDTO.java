package net.avanishkpandey.universum.continentservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CountryStatsDTO implements Serializable {
    private Integer year;
    private Long population;
    private BigDecimal gdp;
}
