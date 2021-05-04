package net.avanishkpandey.universum.continentservice.domain.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Cache(region = "LANGUAGE_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "languages")
public class Language implements Serializable {
	private static final long serialVersionUID = -8833432226691009375L;

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
