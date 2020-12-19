package net.avanishkpandey.universum.continentservice.service;

import net.avanishkpandey.universum.continentservice.dto.ContinentDTO;
import net.avanishkpandey.universum.continentservice.repository.ContinentRepository;
import net.avanishkpandey.universum.continentservice.util.GraphName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContinentService {
    @Autowired
    private ContinentRepository continentRepository;

    public List<ContinentDTO> findAllContinents() {
        return Optional.of(continentRepository.findByIdNotNull()).orElseGet(Collections::emptyList).stream()
                .map(ContinentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ContinentDTO findContinentByID(final Long continentId) {
        return continentRepository.findByIdWithGraphName(continentId, GraphName.CONTINENT_WITH_REGIONS_AND_COUNTRIES)
                .map(ContinentDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No continent found."));
    }
}
