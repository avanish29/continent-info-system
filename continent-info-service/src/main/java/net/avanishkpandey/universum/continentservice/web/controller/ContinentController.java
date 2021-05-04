package net.avanishkpandey.universum.continentservice.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.avanishkpandey.universum.continentservice.domain.dto.ContinentResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.service.ContinentService;
import net.avanishkpandey.universum.continentservice.util.Chronometer;
import net.avanishkpandey.universum.continentservice.util.PathConstants;

@RestController
public class ContinentController {
	private static final Logger log = LoggerFactory.getLogger(ContinentController.class);

	@Autowired
	private ContinentService continentService;

	@GetMapping(PathConstants.CONTINENTS)
	public SearchPageResponse<ContinentResponse> findAll(final SearchPageRequest searchPageRequest) {
		Chronometer chronometer = Chronometer.start();
		SearchPageResponse<ContinentResponse> allContinent = continentService.findAllContinents(searchPageRequest);
		log.info("{} entities retrieved in {} MS.", allContinent.getContents().size(), chronometer.stop());
		return allContinent;
	}

	@GetMapping(PathConstants.CONTINENT_BY_ID)
	public ContinentResponse findById(@PathVariable Long continentId) {
		Chronometer chronometer = Chronometer.start();
		ContinentResponse continent = continentService.findContinentByID(continentId);
		log.info("Find continent by primary key {} in {} MS.", continentId, chronometer.stop());
		return continent;
	}

	@GetMapping(PathConstants.REGIONS_BY_CONTINENT)
	public List<RegionResponse> findAllRegionsByContinent(@PathVariable Long continentId) {
		return continentService.findRegionsByContinent(continentId);
	}
}
