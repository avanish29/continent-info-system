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
public class CountryLanguageId implements Serializable {
	private static final long serialVersionUID = 3842389734720476916L;

	@Column(name = "country_id")
	private Long countryId;

	@Column(name = "language_id")
	private Long languageId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CountryLanguageId that = (CountryLanguageId) o;

		if (!Objects.equals(countryId, that.countryId))
			return false;
		return Objects.equals(languageId, that.languageId);
	}

	@Override
	public int hashCode() {
		int result;
		result = (countryId != null ? countryId.hashCode() : 0);
		result = 31 * result + (languageId != null ? languageId.hashCode() : 0);
		return result;
	}
}
