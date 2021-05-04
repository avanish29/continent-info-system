package net.avanishkpandey.universum.continentservice.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Cache(region = "COUNTRYLANGUAGE_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "country_languages")
public class CountryLanguage implements Serializable {
	private static final long serialVersionUID = -5795111909918987300L;

	@EmbeddedId
	private CountryLanguageId pk = new CountryLanguageId();

	@Column(nullable = false)
	private Boolean official;

	@MapsId("languageId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
	private Language language;

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

		CountryLanguage that = (CountryLanguage) o;

		return getPk() != null ? getPk().equals(that.getPk()) : that.getPk() == null;
	}

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
