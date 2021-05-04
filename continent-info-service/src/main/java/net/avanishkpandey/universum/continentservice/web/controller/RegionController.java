package net.avanishkpandey.universum.continentservice.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.avanishkpandey.universum.continentservice.domain.dto.CountryResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.service.CountryService;
import net.avanishkpandey.universum.continentservice.service.RegionService;
import net.avanishkpandey.universum.continentservice.util.PathConstants;

@RestController
public class RegionController {
    @Autowired
    private RegionService regionService;

    @Autowired
    private CountryService countryService;

    @GetMapping(PathConstants.REGIONS)
    public List<RegionResponse> findAll() {
        return regionService.findAllRegions();
    }

    @GetMapping(PathConstants.REGION_BY_ID)
    public RegionResponse findById(@PathVariable Long regionId) {
        return regionService.findById(regionId);
    }

    @GetMapping(PathConstants.COUNTRIES_BY_REGION)
    public List<CountryResponse> findAllCountriesByRegion(@PathVariable Long regionId) {
        return countryService.findCountriesByRegionId(regionId);
    }
}
