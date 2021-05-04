package net.avanishkpandey.universum.continentservice.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.avanishkpandey.universum.continentservice.domain.dto.AthleteResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.domain.exception.NotFoundException;
import net.avanishkpandey.universum.continentservice.domain.mapper.AthletesMapper;
import net.avanishkpandey.universum.continentservice.domain.model.Athlete;
import net.avanishkpandey.universum.continentservice.repository.AthleteRepository;
import net.avanishkpandey.universum.continentservice.repository.spec.SpecificationBuilder;

@Service
@Slf4j
public class AthleteService {
	public static final Function<Athlete, AthleteResponse> CONVERTER = AthletesMapper.INSTANCE::toModel;

	@Autowired
	private AthleteRepository athleteRepository;

	public SearchPageResponse<AthleteResponse> findAllAthletes(final SearchPageRequest searchPageRequest) {
		log.info("Find all athletes request {}", searchPageRequest);
		Specification<Athlete> searchQuerySpecification = new SpecificationBuilder<Athlete>()
				.with(searchPageRequest.getQuery()).build();
		Page<Athlete> pageResult = athleteRepository.findAll(searchQuerySpecification, searchPageRequest.pageable());
		SearchPageResponse<AthleteResponse> pageResponse = SearchPageResponse.of(pageResult, CONVERTER);
		log.info("Find all athletes response {}", pageResponse);
		return pageResponse;
	}

	public AthleteResponse findAthleteById(final String athleteId) {
		log.info("Find single athletes by ID {}", athleteId);
		Optional<Athlete> result = athleteRepository.findById(athleteId);
		log.info("Find single athletes by ID {} response {}", athleteId,
				result.isPresent() ? result.get() : "No Record found!");
		return CONVERTER.apply(result.orElseThrow(() -> new NotFoundException(Athlete.class, athleteId)));
	}
}
