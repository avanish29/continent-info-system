package net.avanishkpandey.universum.continentservice.dto;

import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.entity.CountryLanguage;

import java.io.Serializable;

@Data
@Builder
public class CountryLanguageDTO implements Serializable {
    private String language;
    private Boolean official;

    public static CountryLanguageDTO fromEntity(final CountryLanguage entity) {
        return CountryLanguageDTO.builder()
                .language(entity.getLanguage().getName())
                .official(entity.getOfficial()).build();
    }
}
