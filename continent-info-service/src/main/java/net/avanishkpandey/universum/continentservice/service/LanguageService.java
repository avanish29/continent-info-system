package net.avanishkpandey.universum.continentservice.service;

import net.avanishkpandey.universum.continentservice.dto.LanguageDTO;
import net.avanishkpandey.universum.continentservice.repository.CountryLanguageRepository;
import net.avanishkpandey.universum.continentservice.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CountryLanguageRepository countryLanguageRepository;

    public List<LanguageDTO> findAllLanguages() {
        return Optional.of(languageRepository.findAll()).orElseGet(Collections::emptyList).stream()
                .map(language -> LanguageDTO.builder().id(language.getId()).language(language.getName()).build())
                .collect(Collectors.toList());
    }

    public LanguageDTO findLanguageByID(final Long languageId) {
        return languageRepository.findById(languageId)
                .map(language -> LanguageDTO.builder().id(language.getId()).language(language.getName()).build())
                .orElseThrow(() -> new EntityNotFoundException("No language found."));
    }

    public LanguageDTO findLanguageByName(final String languageName) {
        return languageRepository.findByNameIgnoreCase(languageName)
                .map(language -> LanguageDTO.builder().id(language.getId()).language(language.getName()).build())
                .orElseThrow(() -> new EntityNotFoundException("No language found."));
    }

    public List<LanguageDTO> findAllLanguagesByCountry(final Long countryId) {
        return Optional.of(countryLanguageRepository.findByPkCountryId(countryId)).orElseGet(Collections::emptyList).stream()
                .map(countryLanguage -> LanguageDTO.builder().id(countryLanguage.getPk().getLanguage().getId())
                        .language(countryLanguage.getPk().getLanguage().getName())
                        .countryName(countryLanguage.getPk().getCountry().getName())
                        .official(countryLanguage.getOfficial()).build())
                .collect(Collectors.toList());
    }
}
