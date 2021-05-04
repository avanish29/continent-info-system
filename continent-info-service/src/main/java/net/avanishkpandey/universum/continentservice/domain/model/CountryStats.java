package net.avanishkpandey.universum.continentservice.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Cache(region = "COUNTRYSTATS_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "country_stats")
public class CountryStats implements Serializable {
	private static final long serialVersionUID = 3204268430205717963L;

	@EmbeddedId
	private CountryStatsId pk = new CountryStatsId();

	private Long population;

	private BigDecimal gdp;

	@MapsId("countryId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CountryStats that = (CountryStats) o;

		return getPk() != null ? getPk().equals(that.getPk()) : that.getPk() == null;
	}

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
