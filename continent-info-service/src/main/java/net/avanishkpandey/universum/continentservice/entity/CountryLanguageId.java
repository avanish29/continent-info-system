package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CountryLanguageId implements Serializable {
    /*@ManyToOne
    private Country country;

    @ManyToOne
    private Language language;*/

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "language_id")
    private Long languageId;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryLanguageId that = (CountryLanguageId) o;

        if (!Objects.equals(countryId, that.countryId)) return false;
        return Objects.equals(languageId, that.languageId);
    }

    public int hashCode() {
        int result;
        result = (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (languageId != null ? languageId.hashCode() : 0);
        return result;
    }
}
