package net.avanishkpandey.universum.continentservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LanguageDTO implements Serializable {
    private Long id;
    private String language;
}
