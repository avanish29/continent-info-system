package net.avanishkpandey.universum.continentservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Cacheable
@org.hibernate.annotations.Cache(region = "LANGUAGE_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "languages")
public class Language implements Serializable {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Language is required.")
    @Column(name = "language")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.languageId")
    private Set<CountryLanguage> countryLanguages;
}
