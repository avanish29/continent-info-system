package net.avanishkpandey.universum.continentservice.web.controller;

import net.avanishkpandey.universum.continentservice.domain.dto.CountryResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.service.CountryService;
import net.avanishkpandey.universum.continentservice.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "regions")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RegionResponse> findAll() {
        return regionService.findAllRegions();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{regionId}")
    public RegionResponse findById(@PathVariable Long regionId) {
        return regionService.findById(regionId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{regionId}/countries")
    public List<CountryResponse> findAllCountriesByRegion(@PathVariable Long regionId) {
        return countryService.findCountriesByRegionId(regionId);
    }
}
