package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.CountryDTO;
import net.avanishkpandey.universum.continentservice.dto.RegionDTO;
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
    public List<RegionDTO> findAll() {
        return regionService.findAllRegions();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{regionId}")
    public RegionDTO findById(@PathVariable Long regionId) {
        return regionService.findById(regionId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{regionId}/countries")
    public List<CountryDTO> findAllCountriesByRegion(@PathVariable Long regionId) {
        return countryService.findCountriesByRegionId(regionId);
    }
}
