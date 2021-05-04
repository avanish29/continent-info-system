package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.domain.model.CountryStats;

@Data
@Builder
@AllArgsConstructor
public class CountryStatsResponse implements Serializable {
	private static final long serialVersionUID = -6397785291309733353L;
	private Integer year;
	private Long population;
	private BigDecimal gdp;

	public static CountryStatsResponse fromEntity(final CountryStats entity) {
		return CountryStatsResponse.builder().year(entity.getPk().getYear()).population(entity.getPopulation())
				.gdp(entity.getGdp()).build();
	}
}
