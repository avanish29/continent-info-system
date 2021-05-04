package net.avanishkpandey.universum.continentservice.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.avanishkpandey.universum.continentservice.domain.dto.ContinentResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.domain.mapper.ContinentMapper;
import net.avanishkpandey.universum.continentservice.domain.model.Continent;
import net.avanishkpandey.universum.continentservice.repository.ContinentRepository;
import net.avanishkpandey.universum.continentservice.repository.RegionRepository;
import net.avanishkpandey.universum.continentservice.repository.spec.SpecificationBuilder;

@Service
@Slf4j
public class ContinentService {
	public static final Function<Continent, ContinentResponse> OF = ContinentMapper.INSTANCE::toModel;

	@Autowired
	private ContinentRepository continentRepository;

	@Autowired
	private RegionRepository regionRepository;

	public SearchPageResponse<ContinentResponse> findAllContinents(final SearchPageRequest searchPageRequest) {
		log.info("Find all continents request {}", searchPageRequest);
		Specification<Continent> searchQuerySpecification = new SpecificationBuilder<Continent>().with(searchPageRequest.getQuery()).build();
		Page<Continent> pageResult = continentRepository.findAll(searchQuerySpecification, searchPageRequest.pageable());
		SearchPageResponse<ContinentResponse> pageResponse = SearchPageResponse.of(pageResult, OF);
		log.info("Find all continents response {}", pageResponse);
		return pageResponse;
	}

	public ContinentResponse findContinentByID(final Long continentId) {
		return continentRepository.findById(continentId)
								  .map(OF)
								  .orElseThrow(() -> new EntityNotFoundException("No continent found."));
	}

	public List<RegionResponse> findRegionsByContinent(final Long continentId) {
		return regionRepository.findByContinentId(continentId)
							   .stream()
							   .map(RegionResponse.of)
							   .collect(Collectors.toList());
	}
}
