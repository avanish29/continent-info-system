package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.language")
    private Set<CountryLanguage> countryLanguages = new HashSet<>(0);
}
