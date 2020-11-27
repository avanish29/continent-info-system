package net.avanishkpandey.universum.continentservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CountryDTO implements Serializable {
    private Long id;
    private String name;
    private BigDecimal area;
    private LocalDate nationalDay;
    private String alpha2Code;
    private String alpha3Code;
    private Set<CountryLanguageDTO> languages;
}
