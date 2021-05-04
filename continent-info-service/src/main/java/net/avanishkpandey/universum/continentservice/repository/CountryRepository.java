package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.domain.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, BaseGraphRepository<Country, Long>, JpaSpecificationExecutor<Country> {
    @EntityGraph(attributePaths = { "region", "countryLanguages", "statistics", "countryLanguages.language.name", "region.continent.name" })
    Page<Country> findByIdNotNull(final Pageable pageable);

    List<Country> findByRegionId(final Long regionId);

    List<Country> findByRegionNameIgnoreCase(final String regionName);

    Optional<Country> findByNameIgnoreCase(final String countryName);
}
