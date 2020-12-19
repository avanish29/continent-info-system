package net.avanishkpandey.universum.continentservice.service;

import net.avanishkpandey.universum.continentservice.dto.CountryDTO;
import net.avanishkpandey.universum.continentservice.dto.PageDTO;
import net.avanishkpandey.universum.continentservice.entity.Country;
import net.avanishkpandey.universum.continentservice.repository.CountryRepository;
import net.avanishkpandey.universum.continentservice.util.GraphName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public PageDTO<CountryDTO> findAllCountries(final int page, final int size) {
        Page<Country> pagedEntity = countryRepository.findAll(PageRequest.of(page, size));
        List<CountryDTO> countryDTOS = Optional.of(pagedEntity.getContent())
                .orElseGet(Collections::emptyList).stream()
                .map(CountryDTO::fromEntity)
                .collect(Collectors.toList());
        return new PageDTO<CountryDTO>(pagedEntity.getTotalElements(), pagedEntity.getTotalPages(), pagedEntity.getNumber(), countryDTOS);
    }

    public CountryDTO findById(final Long countryId) {
        return countryRepository.findByIdWithGraphName(countryId, GraphName.COUNTRY_WITH_LANGUAGE_AND_STATISTICS)
                .map(CountryDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No country found."));
    }

    public List<CountryDTO> findCountriesByRegionId(final Long regionId) {
        return Optional.of(countryRepository.findByRegionId(regionId)).orElseGet(Collections::emptyList).stream()
                .map(CountryDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
