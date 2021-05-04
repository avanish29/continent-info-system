package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.domain.model.CountryLanguage;
import net.avanishkpandey.universum.continentservice.domain.model.CountryLanguageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageId> {
    List<CountryLanguage> findByPkCountryId(final Long countryId);
}
