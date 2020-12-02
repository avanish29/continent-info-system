package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByRegionId(final Long regionId);

    List<Country> findByRegionNameIgnoreCase(final String regionName);

    Optional<Country> findByNameIgnoreCase(final String countryName);
}
