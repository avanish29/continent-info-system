package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.domain.model.CountryLanguage;

@Data
@Builder
@AllArgsConstructor
public class CountryLanguageResponse implements Serializable {
	private static final long serialVersionUID = -8967747090404089965L;
	private String language;
	private Boolean official;

	public static CountryLanguageResponse fromEntity(final CountryLanguage entity) {
		return CountryLanguageResponse.builder().language(entity.getLanguage().getName()).official(entity.getOfficial())
				.build();
	}
}
