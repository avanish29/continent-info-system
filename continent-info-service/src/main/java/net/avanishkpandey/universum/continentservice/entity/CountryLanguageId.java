package net.avanishkpandey.universum.continentservice.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CountryLanguageId implements Serializable {
    @ManyToOne
    private Country country;

    @ManyToOne
    private Language language;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryLanguageId that = (CountryLanguageId) o;

        if (!Objects.equals(country, that.country)) return false;
        return Objects.equals(language, that.language);
    }

    public int hashCode() {
        int result;
        result = (country != null ? country.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
