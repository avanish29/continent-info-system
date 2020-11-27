package net.avanishkpandey.universum.continentservice.service;

import net.avanishkpandey.universum.continentservice.dto.LanguageDTO;
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

    public List<LanguageDTO> findAllLanguages() {
        return Optional.ofNullable(languageRepository.findAll()).orElseGet(Collections::emptyList).stream()
                .map(language -> new LanguageDTO(language.getId(), language.getName()))
                .collect(Collectors.toList());
    }

    public LanguageDTO findLanguageByID(final Long languageId) {
        return languageRepository.findById(languageId)
                .map(language -> new LanguageDTO(language.getId(), language.getName()))
                .orElseThrow(() -> new EntityNotFoundException("No language found."));
    }
}
