package net.avanishkpandey.universum.continentservice.service;

import net.avanishkpandey.universum.continentservice.dto.ContinentDTO;
import net.avanishkpandey.universum.continentservice.dto.CountryDTO;
import net.avanishkpandey.universum.continentservice.dto.CountryLanguageDTO;
import net.avanishkpandey.universum.continentservice.dto.RegionDTO;
import net.avanishkpandey.universum.continentservice.entity.Continent;
import net.avanishkpandey.universum.continentservice.entity.Country;
import net.avanishkpandey.universum.continentservice.entity.CountryLanguage;
import net.avanishkpandey.universum.continentservice.entity.Region;
import net.avanishkpandey.universum.continentservice.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContinentService {
    @Autowired
    private ContinentRepository continentRepository;

    public List<ContinentDTO> findAllContinents() {
        return Optional.ofNullable(continentRepository.findAll()).orElseGet(Collections::emptyList).stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public ContinentDTO findContinentByID(final Long continentId) {
        return continentRepository.findById(continentId)
                .map(this::convertEntityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("No continent found."));
    }

    protected ContinentDTO convertEntityToDTO(final Continent entity) {
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setId(entity.getId());
        continentDTO.setName(entity.getName());
        continentDTO.setRegions(Optional.ofNullable(entity.getRegions()).orElseGet(Collections::emptyList).stream()
                .map(this::convertRegionEntityToDTO).collect(Collectors.toList()));
        return continentDTO;
    }

    protected RegionDTO convertRegionEntityToDTO(final Region entity) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(entity.getId());
        regionDTO.setName(entity.getName());
        regionDTO.setArea(entity.getRegionArea());
        regionDTO.setCountries(Optional.ofNullable(entity.getCountries()).orElseGet(Collections::emptyList).stream()
                .map(this::convertCountryEntityToDTO).collect(Collectors.toList()));
        return regionDTO;
    }

    protected CountryDTO convertCountryEntityToDTO(final Country entity) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(entity.getId());
        countryDTO.setName(entity.getName());
        countryDTO.setArea(entity.getArea());
        countryDTO.setNationalDay(entity.getNationalDay());
        countryDTO.setAlpha2Code(entity.getAlpha2Code());
        countryDTO.setAlpha3Code(entity.getAlpha3Code());
        Set<CountryLanguage> languages = entity.getCountryLanguages();
        countryDTO.setLanguages(Optional.ofNullable(languages).orElseGet(Collections::emptySet).stream()
                .map(this::convertCountryLanguageEntityToDTO).collect(Collectors.toSet()));
        return countryDTO;
    }

    protected CountryLanguageDTO convertCountryLanguageEntityToDTO(final CountryLanguage entity) {
        CountryLanguageDTO countryLanguageDTO = new CountryLanguageDTO();
        countryLanguageDTO.setLanguage(entity.getPk().getLanguage().getName());
        countryLanguageDTO.setOfficial(entity.getOfficial());
        return countryLanguageDTO;
    }
}
