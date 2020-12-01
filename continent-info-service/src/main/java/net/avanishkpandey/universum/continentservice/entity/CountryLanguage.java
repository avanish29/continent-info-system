package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "country_languages")
@AssociationOverrides({
        @AssociationOverride(name = "pk.country", joinColumns = @JoinColumn(name = "country_id")),
        @AssociationOverride(name = "pk.language", joinColumns = @JoinColumn(name = "language_id"))
})
public class CountryLanguage implements Serializable {
    @EmbeddedId
    private CountryLanguageId pk = new CountryLanguageId();

    @Column(nullable = false)
    private Boolean official;

    @Transient
    public Language getLanguage() {
        return pk.getLanguage();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CountryLanguage that = (CountryLanguage) o;

        return getPk() != null ? getPk().equals(that.getPk()) : that.getPk() == null;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
