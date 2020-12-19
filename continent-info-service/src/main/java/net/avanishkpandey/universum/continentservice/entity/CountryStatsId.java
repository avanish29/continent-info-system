package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CountryStatsId implements Serializable {
    /*@ManyToOne
    private Country country;*/

    @Column(name = "country_id")
    private Long countryId;

    @Column(nullable = false)
    private Integer year;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryStatsId that = (CountryStatsId) o;

        if (!Objects.equals(countryId, that.countryId)) return false;
        return Objects.equals(year, that.year);
    }

    public int hashCode() {
        int result;
        result = (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
