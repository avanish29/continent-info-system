package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByNameIgnoreCase(final String languageName);
}
