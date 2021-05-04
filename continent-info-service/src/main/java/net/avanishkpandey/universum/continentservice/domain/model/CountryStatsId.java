package net.avanishkpandey.universum.continentservice.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CountryStatsId implements Serializable {
	private static final long serialVersionUID = 533997838130458835L;

	@Column(name = "country_id")
	private Long countryId;

	@Column(nullable = false)
	private Integer year;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CountryStatsId that = (CountryStatsId) o;

		if (!Objects.equals(countryId, that.countryId))
			return false;
		return Objects.equals(year, that.year);
	}

	@Override
	public int hashCode() {
		int result;
		result = (countryId != null ? countryId.hashCode() : 0);
		result = 31 * result + (year != null ? year.hashCode() : 0);
		return result;
	}
}
