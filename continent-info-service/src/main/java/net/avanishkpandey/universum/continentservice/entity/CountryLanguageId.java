package net.avanishkpandey.universum.continentservice.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

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

        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (country != null ? country.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
