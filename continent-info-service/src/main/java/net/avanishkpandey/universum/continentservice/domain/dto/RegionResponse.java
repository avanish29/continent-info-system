package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.avanishkpandey.universum.continentservice.domain.model.Region;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegionResponse implements Serializable {
	private static final long serialVersionUID = -6573384804580790922L;
	private Long id;
	private String name;
	private BigDecimal area;
	private String continent;
	private List<CountryResponse> countries;

	public static final Function<Region, RegionResponse> of = (final Region entity) -> RegionResponse.builder()
			.id(entity.getId()).name(entity.getName()).area(entity.getRegionArea())
			.continent(entity.getContinent().getName()).build();
}
