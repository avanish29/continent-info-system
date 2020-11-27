package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CountryStatsId implements Serializable {
    @ManyToOne
    private Country country;

    @Column(nullable = false)
    private Integer year;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryStatsId that = (CountryStatsId) o;

        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (country != null ? country.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
