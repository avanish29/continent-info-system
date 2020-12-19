package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.entity.Continent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long>, BaseGraphRepository<Continent, Long> {
    @EntityGraph(attributePaths = {"regions", "regions.countries",
            "regions.countries.countryLanguages", "regions.countries.statistics",
            "regions.countries.countryLanguages.language"})
    List<Continent> findByIdNotNull();
}
