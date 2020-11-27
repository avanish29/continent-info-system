package net.avanishkpandey.universum.continentservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
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
    @JoinColumn(name ="region_id")
    private Region region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.country", cascade = CascadeType.ALL)
    private Set<CountryLanguage> countryLanguages = new HashSet<>(0);

}
