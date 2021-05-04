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

import lombok.extern.slf4j.Slf4j;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryLanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.domain.model.Country;
import net.avanishkpandey.universum.continentservice.repository.CountryLanguageRepository;
import net.avanishkpandey.universum.continentservice.repository.CountryRepository;
import net.avanishkpandey.universum.continentservice.repository.spec.SpecificationBuilder;
import net.avanishkpandey.universum.continentservice.util.GraphName;

@Service
@Slf4j
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CountryLanguageRepository countryLanguageRepository;

	public SearchPageResponse<CountryResponse> findAllCountries(final SearchPageRequest searchPageRequest) {
		log.info("Find all country request {}", searchPageRequest);
		Specification<Country> searchQuerySpecification = new SpecificationBuilder<Country>()
				.with(searchPageRequest.getQuery()).build();
		Page<Country> pageResult = countryRepository.findAll(searchQuerySpecification, searchPageRequest.pageable());
		SearchPageResponse<CountryResponse> pageResponse = SearchPageResponse.of(pageResult,
				CountryResponse.fromEntity);
		log.info("Find all country response {}", pageResponse);
		return pageResponse;
	}

	public CountryResponse findById(final Long countryId) {
		return countryRepository.findByIdWithGraphName(countryId, GraphName.COUNTRY_WITH_LANGUAGE_AND_STATISTICS)
				.map(CountryResponse.fromEntity).orElseThrow(() -> new EntityNotFoundException("No country found."));
	}

	public List<CountryResponse> findCountriesByRegionId(final Long regionId) {
		return Optional.of(countryRepository.findByRegionId(regionId)).orElseGet(Collections::emptyList).stream()
				.map(CountryResponse.fromEntity).collect(Collectors.toList());
	}

	public List<CountryLanguageResponse> findCountryLanguages(final Long countryId) {
		return Optional.of(countryLanguageRepository.findByPkCountryId(countryId)).orElseGet(Collections::emptyList)
				.stream().map(CountryLanguageResponse::fromEntity).collect(Collectors.toList());
	}
}
