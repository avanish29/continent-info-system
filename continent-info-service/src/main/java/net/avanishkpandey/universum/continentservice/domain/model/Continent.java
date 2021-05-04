package net.avanishkpandey.universum.continentservice.domain.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Cache(region = "CONTINENT_CACHE_REGION", usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "continents")
@NamedQueries({ @NamedQuery(name = Continent.FIND_ALL_CONTINENT, query = "SELECT c FROM Continent c") })
@NamedEntityGraph(name = "continent_regions_countries_language_statistics", attributeNodes = {
		@NamedAttributeNode(value = "regions", subgraph = "regionGraph") }, subgraphs = {
				@NamedSubgraph(name = "regionGraph", attributeNodes = {
						@NamedAttributeNode(value = "countries", subgraph = "countryGraph") }),
				@NamedSubgraph(name = "countryGraph", attributeNodes = {
						@NamedAttributeNode(value = "countryLanguages", subgraph = "countryLanguage"),
						@NamedAttributeNode(value = "statistics") }),
				@NamedSubgraph(name = "countryLanguage", attributeNodes = {
						@NamedAttributeNode(value = "language") }) })
public class Continent implements Serializable {
	private static final long serialVersionUID = -5787933280205760151L;

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
