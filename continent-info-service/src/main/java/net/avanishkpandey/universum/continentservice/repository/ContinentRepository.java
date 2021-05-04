package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.domain.model.Continent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long>, BaseGraphRepository<Continent, Long>, JpaSpecificationExecutor<Continent> {
    @EntityGraph(attributePaths = {"regions", "regions.countries",
            "regions.countries.countryLanguages", "regions.countries.statistics",
            "regions.countries.countryLanguages.language"})
    @Cacheable(value = "CONTINENT_CACHE_REGION")
    List<Continent> findByIdNotNull();
}
