package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Cache(region = "CONTINENT_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "continents")
@NamedQueries({
        @NamedQuery(name = Continent.FIND_ALL_CONTINENT, query = "SELECT c FROM Continent c")
})
@NamedEntityGraph(
        name = "continent_regions_countries_language_statistics",
        attributeNodes = {
            @NamedAttributeNode(value = "regions", subgraph = "regionGraph")
        },
        subgraphs = {
            @NamedSubgraph(name = "regionGraph", attributeNodes = {
                    @NamedAttributeNode(value = "countries", subgraph = "countryGraph")
            }),
            @NamedSubgraph(name = "countryGraph", attributeNodes = {
                    @NamedAttributeNode(value = "countryLanguages", subgraph = "countryLanguage"),
                    @NamedAttributeNode(value = "statistics")
            }),
            @NamedSubgraph(name = "countryLanguage", attributeNodes = {
                    @NamedAttributeNode(value = "language")
            })
        }
    )
public class Continent implements Serializable {
    public static final String FIND_ALL_CONTINENT = "Continent.findAll";

    @Id
    @Column(name = "continent_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Continent name is required.")
    private String name;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Region> regions;
}
