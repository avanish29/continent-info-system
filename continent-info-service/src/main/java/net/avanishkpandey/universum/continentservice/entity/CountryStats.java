package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "country_stats")
@AssociationOverrides({
        @AssociationOverride(name = "pk.country", joinColumns = @JoinColumn(name = "country_id"))
})
public class CountryStats implements Serializable {
    @EmbeddedId
    private CountryStatsId pk = new CountryStatsId();

    private Long population;

    private BigDecimal gdp;

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CountryStats that = (CountryStats) o;

        return getPk() != null ? getPk().equals(that.getPk()) : that.getPk() == null;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
