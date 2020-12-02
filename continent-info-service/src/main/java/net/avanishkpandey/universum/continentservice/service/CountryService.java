package net.avanishkpandey.universum.continentservice.service;

import net.avanishkpandey.universum.continentservice.dto.CountryDTO;
import net.avanishkpandey.universum.continentservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<CountryDTO> findAllCountries() {
        return Optional.of(countryRepository.findAll()).orElseGet(Collections::emptyList).stream()
                .map(CountryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CountryDTO findById(final Long countryId) {
        return countryRepository.findById(countryId)
                .map(CountryDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No country found."));
    }

    public List<CountryDTO> findCountriesByRegionId(final Long regionId) {
        return Optional.of(countryRepository.findByRegionId(regionId)).orElseGet(Collections::emptyList).stream()
                .map(CountryDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
