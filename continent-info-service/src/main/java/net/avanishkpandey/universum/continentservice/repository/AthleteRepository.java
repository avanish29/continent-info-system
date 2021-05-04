package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.domain.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, String>, JpaSpecificationExecutor<Athlete> {
}
