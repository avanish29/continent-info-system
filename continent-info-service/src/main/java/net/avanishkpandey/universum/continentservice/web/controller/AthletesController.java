package net.avanishkpandey.universum.continentservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.avanishkpandey.universum.continentservice.domain.dto.AthleteResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.service.AthleteService;
import net.avanishkpandey.universum.continentservice.util.PathConstants;

@RestController
public class AthletesController {
    @Autowired
    private AthleteService athleteService;

    @GetMapping(PathConstants.ATHLETES)
    public SearchPageResponse<AthleteResponse> getAllAthletes(SearchPageRequest searchPageRequest) {
        return athleteService.findAllAthletes(searchPageRequest);
    }

    @GetMapping(PathConstants.ATHLETE_BY_ID)
    public AthleteResponse findAthleteById(@PathVariable String athleteId) {
        return athleteService.findAthleteById(athleteId);
    }
}
