package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.domain.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region> {
    List<Region> findByContinentId(final Long continentId);

    List<Region> findByContinentNameIgnoreCase(final String continentName);

    Optional<Region> findByNameIgnoreCase(final String regionName);
}
