package net.avanishkpandey.universum.continentservice.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Cache(region = "COUNTRY_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "countries")
@NamedQueries({ @NamedQuery(name = Country.FIND_ALL_COUNTRY, query = "SELECT c FROM Country c") })
@NamedEntityGraph(name = "country_language_statistics", attributeNodes = {
		@NamedAttributeNode(value = "countryLanguages", subgraph = "countryLanguageGraph"),
		@NamedAttributeNode(value = "region", subgraph = "countryRegionGraph"),
		@NamedAttributeNode(value = "statistics") }, subgraphs = {
				@NamedSubgraph(name = "countryRegionGraph", attributeNodes = { @NamedAttributeNode(value = "name"),
						@NamedAttributeNode(value = "continent", subgraph = "regionContinentGraph") }),
				@NamedSubgraph(name = "regionContinentGraph", attributeNodes = { @NamedAttributeNode(value = "name") }),
				@NamedSubgraph(name = "countryLanguageGraph", attributeNodes = {
						@NamedAttributeNode(value = "language") }) })
public class Country implements Serializable {
	private static final long serialVersionUID = -2855614230376149513L;

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
