package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.domain.model.Country;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CountryResponse implements Serializable {
	private static final long serialVersionUID = -8758218781275459166L;
	private Long id;
	private String name;
	private String region;
	private String continent;
	private BigDecimal area;
	private LocalDate nationalDay;
	private String alpha2Code;
	private String alpha3Code;
	private Set<CountryLanguageResponse> languages;
	private Set<CountryStatsResponse> statistics;

	public static final Function<Country, CountryResponse> fromEntity = (final Country entity) -> CountryResponse
			.builder().id(entity.getId()).name(entity.getName()).region(entity.getRegion().getName())
			.area(entity.getArea()).alpha2Code(entity.getAlpha2Code())
			.continent(entity.getRegion().getContinent().getName()).alpha3Code(entity.getAlpha3Code())
			.nationalDay(entity.getNationalDay()).build();

}
