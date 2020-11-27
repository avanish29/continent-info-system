package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

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

    private BigInteger population;

    private BigDecimal gdp;

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CountryStats that = (CountryStats) o;

        if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
