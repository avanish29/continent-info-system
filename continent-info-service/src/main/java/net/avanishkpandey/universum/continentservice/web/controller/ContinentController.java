package net.avanishkpandey.universum.continentservice.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.avanishkpandey.universum.continentservice.domain.dto.ContinentResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.service.ContinentService;
import net.avanishkpandey.universum.continentservice.util.Chronometer;

@RestController
@RequestMapping(path = "/continents")
public class ContinentController {
	private static final Logger log = LoggerFactory.getLogger(ContinentController.class);
	private static final String DEFAULT_SORT_FIELD = "id";

	@Autowired
	private ContinentService continentService;

	@GetMapping
	public SearchPageResponse<ContinentResponse> findAll(
			@PageableDefault(sort = DEFAULT_SORT_FIELD) final SearchPageRequest searchPageRequest) {
		Chronometer chronometer = Chronometer.start();
		SearchPageResponse<ContinentResponse> allContinent = continentService.findAllContinents(searchPageRequest);
		log.info("{} entities retrieved in {} MS.", allContinent.getContents().size(), chronometer.stop());
		return allContinent;
	}

	@GetMapping("/{continentId}")
	public ContinentResponse findById(@PathVariable Long continentId) {
		Chronometer chronometer = Chronometer.start();
		ContinentResponse continent = continentService.findContinentByID(continentId);
		log.info("Find continent by primary key {} in {} MS.", continentId, chronometer.stop());
		return continent;
	}

	@GetMapping("/{continentId}/regions")
	public List<RegionResponse> findAllRegionsByContinent(@PathVariable Long continentId) {
		return continentService.findRegionsByContinent(continentId);
	}
}
