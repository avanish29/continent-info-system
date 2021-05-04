package net.avanishkpandey.universum.continentservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import net.avanishkpandey.universum.continentservice.domain.dto.CountryLanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.LanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.model.Language;
import net.avanishkpandey.universum.continentservice.repository.CountryLanguageRepository;
import net.avanishkpandey.universum.continentservice.repository.LanguageRepository;
import net.avanishkpandey.universum.continentservice.repository.spec.SpecificationBuilder;

@Service
public class LanguageService {
	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private CountryLanguageRepository countryLanguageRepository;

	public List<LanguageResponse> findAllLanguages(final SearchPageRequest searchPageRequest) {
		Specification<Language> searchQuerySpecification = new SpecificationBuilder<Language>()
				.with(searchPageRequest.getQuery()).build();

		Page<Language> languagePage = languageRepository.findAll(searchQuerySpecification,
				searchPageRequest.pageable());
		return languagePage.stream()
				.map(language -> LanguageResponse.builder().id(language.getId()).language(language.getName()).build())
				.collect(Collectors.toList());
	}

	public LanguageResponse findLanguageByID(final Long languageId) {
		return languageRepository.findById(languageId)
				.map(language -> LanguageResponse.builder().id(language.getId()).language(language.getName()).build())
				.orElseThrow(() -> new EntityNotFoundException("No language found."));
	}

	public LanguageResponse findLanguageByName(final String languageName) {
		return languageRepository.findByNameIgnoreCase(languageName)
				.map(language -> LanguageResponse.builder().id(language.getId()).language(language.getName()).build())
				.orElseThrow(() -> new EntityNotFoundException("No language found."));
	}

	public List<CountryLanguageResponse> findAllLanguagesByCountry(final Long countryId) {
		return Optional.of(countryLanguageRepository.findByPkCountryId(countryId)).orElseGet(Collections::emptyList)
				.stream().map(CountryLanguageResponse::fromEntity).collect(Collectors.toList());
	}
}
