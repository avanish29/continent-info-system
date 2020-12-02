package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.ContinentDTO;
import net.avanishkpandey.universum.continentservice.dto.RegionDTO;
import net.avanishkpandey.universum.continentservice.service.ContinentService;
import net.avanishkpandey.universum.continentservice.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/continents")
public class ContinentController {
    @Autowired
    private ContinentService continentService;

    @Autowired
    private RegionService regionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ContinentDTO> findAll() {
        return continentService.findAllContinents();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{continentId}")
    public ContinentDTO findById(@PathVariable Long continentId) {
        return continentService.findContinentByID(continentId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{continentId}/regions")
    public List<RegionDTO> findAllRegionsByContinent(@PathVariable Long continentId) {
        return regionService.findRegionsByContinentId(continentId);
    }
}
