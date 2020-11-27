package net.avanishkpandey.universum.continentservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class RegionDTO implements Serializable {
    private Long id;
    private String name;
    private BigDecimal area;
    private List<CountryDTO> countries;
}
