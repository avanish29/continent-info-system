package net.avanishkpandey.universum.continentservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Cacheable
@org.hibernate.annotations.Cache(region = "COUNTRYSTATS_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "country_stats")
/*@AssociationOverrides({
        @AssociationOverride(name = "pk.country", joinColumns = @JoinColumn(name = "country_id"))
})*/
public class CountryStats implements Serializable {
    @EmbeddedId
    private CountryStatsId pk = new CountryStatsId();

    private Long population;

    private BigDecimal gdp;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

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
