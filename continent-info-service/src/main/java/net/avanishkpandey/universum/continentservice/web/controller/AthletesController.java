package net.avanishkpandey.universum.continentservice.web.controller;

import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.AthleteResponse;
import net.avanishkpandey.universum.continentservice.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/athletes")
public class AthletesController {
    @Autowired
    private AthleteService athleteService;

    @GetMapping
    public SearchPageResponse<AthleteResponse> getAllAthletes(SearchPageRequest searchPageRequest) {
        return athleteService.findAllAthletes(searchPageRequest);
    }

    @GetMapping("/{athleteId}")
    public AthleteResponse findAthleteById(@PathVariable String athleteId) {
        return athleteService.findAthleteById(athleteId);
    }
}
