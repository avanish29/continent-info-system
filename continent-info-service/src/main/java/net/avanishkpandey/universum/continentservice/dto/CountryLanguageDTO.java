package net.avanishkpandey.universum.continentservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryLanguageDTO implements Serializable {
    private String language;
    private Boolean official;
}
