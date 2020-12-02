package net.avanishkpandey.universum.continentservice.service;

import net.avanishkpandey.universum.continentservice.dto.RegionDTO;
import net.avanishkpandey.universum.continentservice.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> findAllRegions() {
        return Optional.of(regionRepository.findAll()).orElseGet(Collections::emptyList).stream()
                .map(RegionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public RegionDTO findById(final Long regionId) {
        return regionRepository.findById(regionId)
                .map(RegionDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No region found."));
    }

    public List<RegionDTO> findRegionsByContinentId(final Long continentId) {
        return Optional.of(regionRepository.findByContinentId(continentId)).orElseGet(Collections::emptyList).stream()
                .map(RegionDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
