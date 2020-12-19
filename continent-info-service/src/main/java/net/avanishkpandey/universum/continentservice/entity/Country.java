package net.avanishkpandey.universum.continentservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Cacheable
@Cache(region = "COUNTRY_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "countries")
@NamedQueries({
        @NamedQuery(name = Country.FIND_ALL_COUNTRY, query = "SELECT c FROM Country c")
})
@NamedEntityGraph(
        name = "country_language_statistics",
        attributeNodes = {
                @NamedAttributeNode(value = "countryLanguages", subgraph = "countryLanguageGraph"),
                @NamedAttributeNode(value = "region", subgraph = "countryRegionGraph"),
                @NamedAttributeNode(value = "statistics")
        },
        subgraphs = {
                @NamedSubgraph(name = "countryRegionGraph", attributeNodes = {
                        @NamedAttributeNode(value = "name"),
                        @NamedAttributeNode(value = "continent", subgraph = "regionContinentGraph")
                }),
                @NamedSubgraph(name = "regionContinentGraph", attributeNodes = {
                        @NamedAttributeNode(value = "name")
                }),
                @NamedSubgraph(name = "countryLanguageGraph", attributeNodes = {
                        @NamedAttributeNode(value = "language")
                })
        }
)
public class Country {
    public static final String FIND_ALL_COUNTRY = "Country.findAll";

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Country name is required.")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal area;

    @Column(name = "national_day")
    private LocalDate nationalDay;

    @Column(name = "country_code2", nullable = false, length = 2)
    private String alpha2Code;

    @Column(name = "country_code3", nullable = false, length = 3)
    private String alpha3Code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.countryId", cascade = CascadeType.ALL)
    private Set<CountryLanguage> countryLanguages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.countryId", cascade = CascadeType.ALL)
    private Set<CountryStats> statistics;

}
